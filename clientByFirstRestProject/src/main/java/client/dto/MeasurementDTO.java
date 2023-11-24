package client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MeasurementDTO {
    private double temperature;
    private boolean isRaining;
    private SensorDTO sensor;
    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "temperature=" + temperature +
                ", isRaining=" + isRaining +
                ", sensor=" + sensor +
                '}';
    }
}
