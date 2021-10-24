package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Patients;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.repository.PatientRepository;

import java.util.Optional;

@Repository
@Profile("db")
public class PatientDaoDbImpl implements PatientDao {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Integer getPatientSize() {
        return Math.toIntExact(patientRepository.count());
    }

    @Override
    public Page<Patients> getPatients(Integer pageSize, Integer page) {
        return patientRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Patients getPatient(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patients save(Patients pat) {
        return patientRepository.save(pat);
    }

    @Override
    public Page<Patients> getPatient(String title, Pageable page) {
        return patientRepository.findByNameIgnoreCaseContaining(title,page);
    }

    @Override
    public Optional<Patients> findById(Long id) {
        return patientRepository.findById(id);
    }
}
