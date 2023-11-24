package web.nuuunikita.project.firstrestproject.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import web.nuuunikita.project.firstrestproject.models.Measurement;
import web.nuuunikita.project.firstrestproject.models.Sensor;
import web.nuuunikita.project.firstrestproject.repositories.MeasurementRepository;
import web.nuuunikita.project.firstrestproject.repositories.SensorRepository;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class MeasurementServiceTest {
    @InjectMocks
    private MeasurementService measurementService;
    @Mock
    private MeasurementRepository measurementRepository;
    private List<Measurement> measurements;

    @BeforeEach
    void init(){
        measurements = List.of(new Measurement(1.2, true),
                new Measurement(2.2, true),
                new Measurement(3.6, true));
    }

    @Test
    void avgTemperature() {
        Mockito.when(measurementRepository.findAll()).thenReturn(measurements);

        double avgTemperature = measurementService.getAvgTemperature();

        Assertions.assertEquals(7.0/3, avgTemperature);
    }

    @Test
    void getRainyDay() {
        Mockito.when(measurementRepository.findAll()).thenReturn(measurements);

        double avgTemperature = measurementService.getCountRainyDay();

        Assertions.assertEquals(2, avgTemperature);
    }

    @Test
    void saveMeasurement() {
        Measurement measurement = new Measurement(44.4, true);
        measurement.setSensor(new Sensor("Sensor 1"));

        measurementService.saveMeasurement(measurement);

        Mockito.verify(measurementRepository, Mockito.times(1)).save(measurement);
    }
}