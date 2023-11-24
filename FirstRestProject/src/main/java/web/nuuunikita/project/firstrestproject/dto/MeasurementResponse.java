package web.nuuunikita.project.firstrestproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter @Getter
public class MeasurementResponse {
    private List<MeasurementDTO> measurementsDTO;

    public MeasurementResponse(List<MeasurementDTO> measurementsDTO) {
        this.measurementsDTO = measurementsDTO;
    }
}
