package se331.lab.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patients;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patients,Long> {
    List<Patients> findAll();
    Page<Patients> findByNameIgnoreCaseContaining(String title, Pageable page);
}
