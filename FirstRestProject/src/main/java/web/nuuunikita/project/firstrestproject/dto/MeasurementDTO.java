package web.nuuunikita.project.firstrestproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class MeasurementDTO {
    private double temperature;
    private boolean raining;
    private SensorDTO sensor;

    public MeasurementDTO(double temperature, boolean raining, SensorDTO sensor) {
        this.temperature = temperature;
        this.raining = raining;
        this.sensor = sensor;
    }
}
