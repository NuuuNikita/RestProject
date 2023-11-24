package client.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter @Getter
public class MeasurementsResponse {
    private List<MeasurementDTO> measurementsDTO;
}
