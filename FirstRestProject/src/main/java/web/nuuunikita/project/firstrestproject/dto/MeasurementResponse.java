package web.nuuunikita.project.firstrestproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter @Getter
public class MeasurementResponse {
    private List<MeasurementDTO> measurementDTOS;

    public MeasurementResponse(List<MeasurementDTO> measurementDTOS) {
        this.measurementDTOS = measurementDTOS;
    }
}
