package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.DoctorDao;
import se331.lab.rest.dao.PatientDao;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Doctors;
import se331.lab.rest.entity.Patients;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    PatientDao patientDao;
    @Override
    public Integer getDoctorSize() {
        return doctorDao.getDoctorSize();
    }

    @Override
    public Page<Doctor> getDoctors(Integer pageSize, Integer page) {
        return doctorDao.getDoctors(pageSize, page);
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorDao.getDoctor(id);
    }

    @Override
    public Doctor save(Doctor doctor) {
        Patients patients = patientDao.findById(doctor.getPatient().getId()).orElse(null);
        patients.setDoctor(doctor);
        doctor.getPatient().add(patients);
        return doctorDao.save(doctor);
    }

    @Override
    public Page<Doctor> getDoctors(String title, Pageable pageable) {
        return doctorDao.getDoctor(title,pageable);
    }
}
