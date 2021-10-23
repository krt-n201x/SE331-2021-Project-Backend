package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.lab.rest.entity.*;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.entity.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);
    PatientDTO getPatientDto(Patients pat);
    List<PatientDTO> getPatientDto(List<Patients> pats);

    DoctorDTO getDoctorDto(Doctor doc);
    List<DoctorDTO> getDoctorDto(List<Doctor> docs);

    VaccineDTO getVaccineDto(Vaccine vac);
    List<PatientDTO> getVaccineDto(List<Vaccine> vacs);

//    @Mapping(target = "authorities",
//            expression = "java(organizer.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
//    OrganizerAuthDTO getOrganizerAuthDTO(Organizer organizer);

    UserDTO getUserDto(User user);
}
