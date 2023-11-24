package web.nuuunikita.project.firstrestproject.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.nuuunikita.project.firstrestproject.dto.MeasurementDTO;
import web.nuuunikita.project.firstrestproject.models.Measurement;

@Component
public class MeasurementMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
