package web.nuuunikita.project.firstrestproject.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 30, message = "name should be between 3 and 30 character")
    private String name;

    @OneToMany(mappedBy = "sensor")
    @JsonManagedReference
    private List<Measurement> measurements;

    public Sensor(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
