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
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.service.VaccineService;
import se331.lab.rest.util.LabMapper;

@Controller
public class VaccineController {
    @Autowired
    VaccineService vaccineService;

    @GetMapping("vaccines/{id}")
    public ResponseEntity<?> getVaccine(@PathVariable("id") Long id) {

        Vaccine output = vaccineService.getVaccine(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getVaccineDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}
