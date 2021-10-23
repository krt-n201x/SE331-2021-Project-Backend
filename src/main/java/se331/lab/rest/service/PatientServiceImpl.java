package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.DoctorDao;
import se331.lab.rest.dao.PatientDao;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patients;

import javax.print.Doc;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientDao patientDao;
    @Autowired
    DoctorDao doctorDao;
    @Override
    public Integer getPatientSize() {
        return patientDao.getPatientSize();
    }

    @Override
    public Page<Patients> getPatients(Integer pageSize, Integer page) {
        return patientDao.getPatients(pageSize, page);
    }

    @Override
    public Patients getPatient(Long id) {
        return patientDao.getPatient(id);
    }

    @Override
    public Patients save(Patients pat) {
        Doctor doc = doctorDao.findById(pat.getDoctor().getId()).orElse(null);
        pat.setDoctor(doc);
        doc.getPatient().add(pat);
        return patientDao.save(pat);
    }

    @Override
    public Page<Patients> getPatients(String title, Pageable pageable) {
        return patientDao.getPatient(title,pageable);
    }
}
