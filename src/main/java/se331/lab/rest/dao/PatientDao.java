package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Patients;
import se331.lab.rest.entity.Vaccine;

import java.util.Optional;

public interface PatientDao {
    Integer getPatientSize();
    Page<Patients> getPatients(Integer pageSize, Integer page);
    Patients getPatient(Long id);
    Patients save(Patients pat);
    Page<Patients> getPatient(String name, Pageable page);
    Optional<Patients> findById(Long id);
}
