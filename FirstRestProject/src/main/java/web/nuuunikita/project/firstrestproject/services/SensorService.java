package web.nuuunikita.project.firstrestproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.nuuunikita.project.firstrestproject.models.Sensor;
import web.nuuunikita.project.firstrestproject.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void saveSensor(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findSensor(Sensor sensor){
        return Optional.ofNullable(sensorRepository.findSensorByName(sensor.getName()));
    }
}
