package client.dto;

import client.models.Sensor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MeasurementDTO {
    private double temperature;
    private boolean isRaining;
    private Sensor sensor;
    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "temperature=" + temperature +
                ", isRaining=" + isRaining +
                ", sensor=" + sensor +
                '}';
    }
}
