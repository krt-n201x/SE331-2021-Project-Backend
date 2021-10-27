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
import se331.lab.rest.dao.PatientDao;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patients;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.service.VaccineService;
import se331.lab.rest.util.LabMapper;

@Controller
public class VaccineController {
    @Autowired
    VaccineService vaccineService;

    @Autowired
    PatientDao patientDao;
    
    @GetMapping("vaccines")
    public ResponseEntity<?> getVaccineLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page, @RequestParam(value = "name", required = false) String title) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Vaccine> pageOutput;
        if (title == null) {
            pageOutput = vaccineService.getVaccines(perPage, page);
        } else {
            pageOutput = vaccineService.getVaccines(title, PageRequest.of(page - 1, perPage));
        }
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getVaccineDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);

    }

    @GetMapping("vaccines/{id}")
    public ResponseEntity<?> getVaccine(@PathVariable("id") Long id) {

        Vaccine output = vaccineService.getVaccine(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getVaccineDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PostMapping("/addvac")
    public ResponseEntity<?> addVac(@RequestBody Vaccine vaccine) {
        Vaccine output = vaccineService.save(vaccine);
        return ResponseEntity.ok(LabMapper.INSTANCE.getVaccineDto(output));
    }
}
