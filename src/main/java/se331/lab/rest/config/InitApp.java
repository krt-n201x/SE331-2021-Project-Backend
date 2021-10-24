package se331.lab.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patients;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.repository.PatientRepository;
import se331.lab.rest.repository.VaccineRepository;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.AuthorityRepository;
import se331.lab.rest.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VaccineRepository vaccineRepository;
    @Autowired
    PatientRepository patientRepository;
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Patients pat1,pat2,pat3;
        Doctor doc1,doc2,doc3;
        pat1 = patientRepository.save(Patients.builder()
                .name("John")
                .surname("Doe")
                .status("Taken 2 doses")
                .age(42)
                .hometown("Chiang Mai, Thailand")
                .doctor_comm("Nothing").build());
        pat2 = patientRepository.save(Patients.builder()
                .name("Will")
                .surname("Smith")
                .status("Taken 1 doses")
                .age(30)
                .hometown("Chiang Rai, Thailand")
                .doctor_comm("I love your muscle").build());
        pat3 = patientRepository.save(Patients.builder()
                .name("Swift")
                .surname("Taylor")
                .status("Taken 1 doses")
                .age(31)
                .hometown("Bangkok, Thailand")
                .doctor_comm("something bangsat").build());
        doc1 = doctorRepository.save(Doctor.builder()
                .name("Peerapat")
                .surname("Thungngoen")
                .age(21)
                .build());
        doc2 = doctorRepository.save(Doctor.builder()
                .name("Kanoknart")
                .surname("Something")
                .age(6)
                .build());
        doc3 = doctorRepository.save(Doctor.builder()
                .name("Kitsada")
                .surname("Something")
                .age(204)
                .build());
        pat1.setDoctor(doc1);
        pat2.setDoctor(doc2);
        pat3.setDoctor(doc3);
        pat1.getImageUrl().add("https://www.img.in.th/images/30ace35710f11b4a7f21f7295e508df5.png");
        pat2.getImageUrl().add("https://www.img.in.th/images/30ace35710f11b4a7f21f7295e508df5.png");
        pat3.getImageUrl().add("https://www.img.in.th/images/a2bf62a37f1666840b96d4b5b636dadf.png");
        Vaccine tempVac;
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Sinovac")
                .date_injected("July 14 2021")
                .build());
        tempVac.setPatient(pat1);
        pat1.getVaccine().add(tempVac);
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Astrazeneca")
                .date_injected("August 12 2021")
                .build());
        tempVac.setPatient(pat1);
        pat1.getVaccine().add(tempVac);
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Sinovac")
                .date_injected("July 14 2021")
                .build());
        tempVac.setPatient(pat2);
        pat2.getVaccine().add(tempVac);
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Sinovac")
                .date_injected("August 14 2021")
                .build());
        tempVac.setPatient(pat2);
        pat2.getVaccine().add(tempVac);
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Sinovac")
                .date_injected("August 20 2021")
                .build());
        tempVac.setPatient(pat3);
        pat3.getVaccine().add(tempVac);
        addUser();

        doc1.setUser(user3);
        user3.setDoctor(doc1);
        doc2.setUser(user1);
        user1.setDoctor(doc2);
    }
    User user1, user2, user3;
    private void addUser(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user3 = User.builder()
                .username("doctor")
                .password(encoder.encode("doctor"))
                .firstname("doctor")
                .lastname("doctor")
                .email("doctor@doctor.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        authorityRepository.save(authUser);
        authorityRepository.save(authAdmin);
        authorityRepository.save(authDoctor);
        user1.getAuthorities().add(authUser);
        user1.getAuthorities().add(authAdmin);
        user2.getAuthorities().add(authUser);
        user3.getAuthorities().add(authUser);
        user3.getAuthorities().add(authDoctor);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

    }
}
