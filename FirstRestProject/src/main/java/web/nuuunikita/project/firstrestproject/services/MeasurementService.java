package web.nuuunikita.project.firstrestproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.nuuunikita.project.firstrestproject.models.Measurement;
import web.nuuunikita.project.firstrestproject.repositories.MeasurementRepository;
import web.nuuunikita.project.firstrestproject.repositories.SensorRepository;


import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    public List<Measurement> getMeasurements(){
        return measurementRepository.findAll();
    }

    @Transactional
    public void saveMeasurement(Measurement measurement){
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement){
        measurement.setSensor(sensorRepository.findSensorByName(measurement.getSensor().getName()));
        measurement.setFixedIn(new Date());
    }

    public long getCountRainyDay(){
        return measurementRepository.findAll().stream()
                .filter(Measurement::isRaining)
                .count();
    }

    public double getAvgTemperature(){
        return measurementRepository.findAll().stream()
                .mapToDouble(Measurement::getTemperature)
                .summaryStatistics()
                .getAverage();
    }
}
