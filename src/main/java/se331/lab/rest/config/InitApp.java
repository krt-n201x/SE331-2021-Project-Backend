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
        Patients pat1,pat2,pat3,pat4, pat5, pat6, pat7, pat8, pat9;
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
                .status("Taken 2 doses")
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
        pat4 = patientRepository.save(Patients.builder()
                .name("Break")
                .surname("Fast")
                .status("Taken 2 doses")
                .age(56)
                .hometown("Bangkok, Thailand")
                .doctor_comm("yes okay").build());
        pat5 = patientRepository.save(Patients.builder()
                .name("Morty")
                .surname("Sanchez")
                .status("Taken 1 doses")
                .age(15)
                .hometown("Bangkok, Thailand")
                .doctor_comm("I love pickle").build());
        pat6 = patientRepository.save(Patients.builder()
                .name("Noah")
                .surname("Titanic")
                .status("Taken 2 doses")
                .age(100)
                .hometown("Bangkok, Thailand")
                .doctor_comm("drowning").build());
        pat7 = patientRepository.save(Patients.builder()
                .name("Katy")
                .surname("Perry")
                .status("Taken 1 doses")
                .age(58)
                .hometown("Bangkok, Thailand")
                .doctor_comm("hot and cold").build());
        pat8 = patientRepository.save(Patients.builder()
                .name("Chick")
                .surname("Ken")
                .status("Taken 1 doses")
                .age(2)
                .hometown("Bangkok, Thailand")
                .doctor_comm("high protein").build());
        pat9 = patientRepository.save(Patients.builder()
                .name("Sushi")
                .surname("Maki")
                .status("Taken 1 doses")
                .age(44)
                .hometown("Bangkok, Thailand")
                .doctor_comm("Yummy yum").build());

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
        pat4.setDoctor(doc1);
        pat5.setDoctor(doc2);
        pat6.setDoctor(doc3);
        pat7.setDoctor(doc1);
        pat8.setDoctor(doc2);
        pat9.setDoctor(doc3);

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
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Sinovac")
                .date_injected("August 22 2021")
                .build());
        tempVac.setPatient(pat4);
        pat4.getVaccine().add(tempVac);
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Sinovac")
                .date_injected("August 30 2021")
                .build());
        tempVac.setPatient(pat4);
        pat4.getVaccine().add(tempVac);
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Sinovac")
                .date_injected("October 22 2021")
                .build());
        tempVac.setPatient(pat5);
        pat5.getVaccine().add(tempVac);
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Sinovac")
                .date_injected("August 7 2021")
                .build());
        tempVac.setPatient(pat6);
        pat6.getVaccine().add(tempVac);
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Moderna")
                .date_injected("August 15 2021")
                .build());
        tempVac.setPatient(pat6);
        pat6.getVaccine().add(tempVac);
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Moderna")
                .date_injected("Janury 2 2021")
                .build());
        tempVac.setPatient(pat7);
        pat7.getVaccine().add(tempVac);
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Sinovac")
                .date_injected("Febuary 14 2021")
                .build());
        tempVac.setPatient(pat8);
        pat8.getVaccine().add(tempVac);
        tempVac = vaccineRepository.save(Vaccine.builder()
                .name("Moderna")
                .date_injected("August 22 2021")
                .build());
        tempVac.setPatient(pat9);
        pat9.getVaccine().add(tempVac);



        addUser();
        doc1.setUser(user3);
        user3.setDoctor(doc1);
        doc2.setUser(user1);
        user1.setDoctor(doc2);
        pat1.setUser(user2);
        user2.setPatient(pat1);
        pat2.setUser(user4);
        user4.setPatient(pat2);
        pat3.setUser(user5);
        user5.setPatient(pat3);
        doc3.setUser(user6);
        user6.setDoctor(doc3);
        pat4.setUser(user7);
        user7.setPatient(pat4);
        pat5.setUser(user8);
        user8.setPatient(pat5);
        pat6.setUser(user9);
        user9.setPatient(pat6);
        pat7.setUser(user10);
        user10.setPatient(pat7);
        pat8.setUser(user11);
        user11.setPatient(pat8);
        pat9.setUser(user12);
        user12.setPatient(pat9);
    }
    User user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12;
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
        user4 = User.builder()
                .username("user2")
                .password(encoder.encode("user2"))
                .firstname("user2")
                .lastname("user2")
                .email("enabled2@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user5 = User.builder()
                .username("user3")
                .password(encoder.encode("user3"))
                .firstname("user3")
                .lastname("user3")
                .email("enabled3@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user6 = User.builder()
                .username("user4")
                .password(encoder.encode("user4"))
                .firstname("user4")
                .lastname("user4")
                .email("enabled4@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user7 = User.builder()
                .username("user5")
                .password(encoder.encode("user5"))
                .firstname("user5")
                .lastname("user5")
                .email("enabled5@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user8 = User.builder()
                .username("user6")
                .password(encoder.encode("user6"))
                .firstname("user6")
                .lastname("user6")
                .email("enabled6@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user9 = User.builder()
                .username("user7")
                .password(encoder.encode("user7"))
                .firstname("user7")
                .lastname("user7")
                .email("enabled7@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user10 = User.builder()
                .username("user8")
                .password(encoder.encode("user8"))
                .firstname("user8")
                .lastname("user8")
                .email("enabled8@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user11 = User.builder()
                .username("user9")
                .password(encoder.encode("user9"))
                .firstname("user9")
                .lastname("user9")
                .email("enabled9@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user12 = User.builder()
                .username("user10")
                .password(encoder.encode("user10"))
                .firstname("user10")
                .lastname("user10")
                .email("enabled10@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        authorityRepository.save(authUser);
        authorityRepository.save(authAdmin);
        authorityRepository.save(authDoctor);
//        user1.getAuthorities().add(authUser);
        user1.getAuthorities().add(authAdmin);
        user2.getAuthorities().add(authUser);
//        user3.getAuthorities().add(authUser);
        user3.getAuthorities().add(authDoctor);
        user4.getAuthorities().add(authUser);
        user5.getAuthorities().add(authUser);
        user6.getAuthorities().add(authUser);
        user7.getAuthorities().add(authUser);
        user8.getAuthorities().add(authUser);
        user9.getAuthorities().add(authUser);
        user10.getAuthorities().add(authUser);
        user11.getAuthorities().add(authUser);
        user12.getAuthorities().add(authUser);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);
        userRepository.save(user10);
        userRepository.save(user11);
        userRepository.save(user12);

    }
}
