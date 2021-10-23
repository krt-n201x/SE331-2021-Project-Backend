package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patients;
import se331.lab.rest.entity.Vaccine;

public interface VaccineService {
    Integer getVaccineSize();
    Page<Vaccine> getVaccines(Integer pageSize, Integer page);
    Vaccine getVaccine(Long id);
    Vaccine save(Vaccine vac);
    Page<Vaccine> getVaccines(String title, Pageable pageable);
}
