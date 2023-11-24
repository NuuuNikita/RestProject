package web.nuuunikita.project.firstrestproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.nuuunikita.project.firstrestproject.models.Sensor;
import web.nuuunikita.project.firstrestproject.repositories.SensorRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Transactional
    public void saveSensor(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findSensor(Sensor sensor){
        return Optional.ofNullable(sensorRepository.findSensorByName(sensor.getName()));
    }
}
