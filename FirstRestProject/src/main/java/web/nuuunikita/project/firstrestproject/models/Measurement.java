package web.nuuunikita.project.firstrestproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = -100)
    @Max(value = 100)
    private double temperature;

    @Column(name = "is_raining")
    private boolean raining;

    @Column(name = "fixed_in")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fixedIn;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    public Measurement(double temperature, boolean raining) {
        this.temperature = temperature;
        this.raining = raining;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", isRaining=" + raining +
                ", fixedIn=" + fixedIn +
                ", sensorDTO=" + sensor +
                '}';
    }
}
