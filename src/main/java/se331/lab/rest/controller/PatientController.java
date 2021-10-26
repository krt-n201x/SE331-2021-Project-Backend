package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.dao.DoctorDao;
import se331.lab.rest.dao.PatientDao;
import se331.lab.rest.dao.VaccineDao;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patients;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.repository.VaccineRepository;
import se331.lab.rest.service.DoctorService;
import se331.lab.rest.service.PatientService;
import se331.lab.rest.util.LabMapper;

@Controller
public class PatientController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    PatientService patientService;
    @Autowired
    PatientDao patientDao;
    @Autowired
    VaccineRepository vaccineRepository;
    @GetMapping("patients")
    public ResponseEntity<?> getPatientLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "name", required = false) String title) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Patients> pageOutput;
        if (title == null) {
            pageOutput = patientService.getPatients(perPage, page);
        } else {
            pageOutput = patientService.getPatients(title, PageRequest.of(page - 1, perPage));
        }
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getPatientDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);

    }

    @GetMapping("patients/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") Long id) {

        Patients output = patientService.getPatient(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @GetMapping("admin")
    public ResponseEntity<?> getUserLists() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(patientService.getAllUserVaccine()));
    }

    @PostMapping("/patients")
    public ResponseEntity<?> addPatient(@RequestBody Patients Patient) {
        Patients output = patientService.save(Patient);
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDto(output));


    }
    @PostMapping("/comment")
    public ResponseEntity<?> addCommentPatient(@RequestBody Patients patient) {
        Patients changeCom = patientDao.findById(patient.getId()).orElse(null);
        changeCom.setDoctor_comm(patient.getDoctor_comm());
        Patients output = patientService.save(changeCom);   
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDto(output));
    }

    @PostMapping("/savedtop")
    public ResponseEntity<?> saveDtoP(@RequestBody Patients patient) {
        Patients changeDoc = patientDao.findById(patient.getId()).orElse(null);
        Doctor doctor = doctorDao.findById(patient.getDoctor().getId()).orElse(null);
        changeDoc.setDoctor(patient.getDoctor());
        doctor.getPatient().add(changeDoc);
//        Doctor outputDoctor = doctorService.save(doctor);
        Patients output = patientService.save(changeDoc);
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDto(output));
    }

    @PostMapping("/savevac")
    public ResponseEntity<?> saveVac(@RequestBody Patients patient) {
        Patients addVac = patientDao.findById(patient.getId()).orElse(null);
        Vaccine vaccine = vaccineRepository.save(Vaccine.builder()
                        .name(patient.getVaccine().get(0).getName())
                .date_injected(patient.getVaccine().get(0).getDate_injected()).build()
                );
        addVac.getVaccine().add(vaccine);
        vaccine.setPatient(addVac);
        Patients output = patientService.save(addVac);
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDto(output));
    }

//    @PostMapping("/savedtop")
//    public ResponseEntity<?> saveDtoP(@RequestBody Doctor doctor) {
//        Doctor changedoctor = doctorDao.findById(doctor.getId()).orElse(null);
//        Patients changeDoc = patientDao.findById(changedoctor.getPatient().).orElse(null);
//
//        changeDoc.setDoctor(patient.getDoctor());
//        doctor.getPatient().add(changeDoc);
////        Doctor outputDoctor = doctorService.save(doctor);
//        Patients output = patientService.save(changeDoc);
//        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDto(output));
//    }

}
