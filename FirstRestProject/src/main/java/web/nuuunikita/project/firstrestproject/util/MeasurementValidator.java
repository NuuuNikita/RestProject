package web.nuuunikita.project.firstrestproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import web.nuuunikita.project.firstrestproject.dto.MeasurementDTO;
import web.nuuunikita.project.firstrestproject.models.Measurement;
import web.nuuunikita.project.firstrestproject.services.SensorService;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurement.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;

        if (measurement.getSensor() == null) {
            return;
        }

        if (sensorService.findSensor(measurement.getSensor()).isEmpty())
            errors.rejectValue("sensorDTO", "Sensor is not registration");
    }
}
