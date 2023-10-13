package web.nuuunikita.project.firstrestproject.repositories;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.nuuunikita.project.firstrestproject.models.Measurement;

import java.util.Date;
import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}
