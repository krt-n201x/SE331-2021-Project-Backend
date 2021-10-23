package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Doctor;

import java.util.Optional;

public interface DoctorDao {
    Integer getDoctorSize();
    Page<Doctor> getDoctors(Integer pageSize, Integer page);
    Doctor getDoctor(Long id);
    Doctor save(Doctor doctor);
    Optional<Doctor> findById(Long id);
    Page<Doctor> getDoctor(String name, Pageable page);
}
