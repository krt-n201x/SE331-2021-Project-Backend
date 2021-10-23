package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Patients;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.repository.VaccineRepository;

import java.util.Optional;

@Repository
@Profile("db")
public class VaccineDaoDbImpl implements VaccineDao {
    @Autowired
    VaccineRepository vaccineRepository;

    @Override
    public Integer getVaccineSize() {
        return Math.toIntExact(vaccineRepository.count());
    }

    @Override
    public Page<Vaccine> getVaccines(Integer pageSize, Integer page) {
        return vaccineRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Vaccine getVaccine(Long id) {
        return vaccineRepository.findById(id).orElse(null);
    }

    @Override
    public Vaccine save(Vaccine vac) {
        return vaccineRepository.save(vac);
    }

    @Override
    public Page<Vaccine> getVaccine(String title, Pageable page) {
        return vaccineRepository.findByNameIgnoreCaseContaining(title,page);
    }

}
