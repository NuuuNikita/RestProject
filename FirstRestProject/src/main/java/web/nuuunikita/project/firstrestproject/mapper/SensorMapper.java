package web.nuuunikita.project.firstrestproject.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.nuuunikita.project.firstrestproject.dto.SensorDTO;
import web.nuuunikita.project.firstrestproject.models.Sensor;

@Component
public class SensorMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public SensorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return this.modelMapper.map(sensorDTO, Sensor.class);
    }

    public SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
