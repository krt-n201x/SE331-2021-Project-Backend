package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patients;
import se331.lab.rest.entity.Vaccine;

import java.util.Optional;

public interface VaccineDao {
    Integer getVaccineSize();
    Page<Vaccine> getVaccines(Integer pageSize, Integer page);
    Vaccine getVaccine(Long id);
    Vaccine save(Vaccine vac);
    Page<Vaccine> getVaccine(String name, Pageable page);
}
