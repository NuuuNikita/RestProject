package web.nuuunikita.project.firstrestproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import web.nuuunikita.project.firstrestproject.models.Sensor;
import web.nuuunikita.project.firstrestproject.repositories.SensorRepository;


@ExtendWith(MockitoExtension.class)
class SensorServiceTest {
    @InjectMocks
    private SensorService sensorService;
    @Mock
    private SensorRepository sensorRepository;
    private Sensor sensor;
    @BeforeEach
    void init(){
        this.sensor = new Sensor("Sensor 1");
    }

    @Test
    void saveSensor() {
        sensorRepository.save(sensor);
        Mockito.verify(sensorRepository, Mockito.times(1)).save(sensor);
    }

    @Test
    void findSensor() {
        sensorRepository.findSensorByName(sensor.getName());
        Mockito.verify(sensorRepository, Mockito.times(1)).findSensorByName(sensor.getName());
    }
}