package se331.lab.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Vaccine;

import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine,Long> {
    List<Vaccine> findAll();
}
