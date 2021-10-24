package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPatientDTO {
    Long id;
    String name;
    String surname;
    String status;
    Integer age;
    String hometown;
    String doctor_comm;
}
