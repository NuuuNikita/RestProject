package web.nuuunikita.project.firstrestproject.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import web.nuuunikita.project.firstrestproject.models.Sensor;
import web.nuuunikita.project.firstrestproject.services.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if(sensor.getName() == null) return;

        if(sensorService.findSensor(sensor).isPresent()){
            errors.rejectValue("name", "A sensor with this name is already registered");
        }
    }
}
