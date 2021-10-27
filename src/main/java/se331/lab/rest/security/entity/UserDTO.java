package se331.lab.rest.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se331.lab.rest.entity.DoctorAuthDTO;
import se331.lab.rest.entity.DoctorDTO;
import se331.lab.rest.entity.PatientAuthDTO;
import se331.lab.rest.entity.PatientDTO;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    String username;
    String password;
    String email;
    List<AuthorityDTO> authorities;
    String id;
}
