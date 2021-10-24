package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.PatientDao;
import se331.lab.rest.dao.VaccineDao;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patients;
import se331.lab.rest.entity.Vaccine;

@Service
public class VaccineServiceImpl implements VaccineService{
    @Autowired
    VaccineDao vaccineDao;
    @Autowired
    PatientDao patientDao;
    @Override
    public Integer getVaccineSize() {
        return vaccineDao.getVaccineSize();
    }

    @Override
    public Page<Vaccine> getVaccines(Integer pageSize, Integer page) {
        return vaccineDao.getVaccines(pageSize, page);
    }

    @Override
    public Vaccine save(Vaccine vac) {
        Patients pat = patientDao.findById(vac.getPatient().getId()).orElse(null);
        vac.setPatient(pat);
        pat.getVaccine().add(vac);
        return vaccineDao.save(vac);
    }

    @Override
    public Vaccine getVaccine(Long id) {
        return vaccineDao.getVaccine(id);
    }

    @Override
    public Page<Vaccine> getVaccines(String title, Pageable pageable) {
        return vaccineDao.getVaccine(title,pageable);
    }
}
