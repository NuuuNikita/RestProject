package web.nuuunikita.project.firstrestproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.nuuunikita.project.firstrestproject.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Sensor findSensorByName(String name);
}
