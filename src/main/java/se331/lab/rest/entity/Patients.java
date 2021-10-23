package se331.lab.rest.entity;

import lombok.*;
import se331.lab.rest.security.entity.User;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String surname;
    String status;
    Integer age;
    String hometown;
    String doctor_comm;
    @OneToOne
    User user;
    @ManyToOne
    Doctor doctor;
    @OneToMany(mappedBy = "patient")
    List<Vaccine> vaccine;
    @ElementCollection
    List<String> imageUrl;
}
