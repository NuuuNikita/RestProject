package web.nuuunikita.project.firstrestproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorDTO {
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }
}
