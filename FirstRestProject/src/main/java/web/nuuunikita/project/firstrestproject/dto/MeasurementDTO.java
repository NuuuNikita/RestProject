package web.nuuunikita.project.firstrestproject.dto;

import lombok.Getter;
import lombok.Setter;
import web.nuuunikita.project.firstrestproject.models.Sensor;

@Getter @Setter
public class MeasurementDTO {
    private double temperature;
    private boolean raining;
    private Sensor sensor;
}
