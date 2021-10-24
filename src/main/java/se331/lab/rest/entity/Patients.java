package se331.lab.rest.entity;

import lombok.*;
import se331.lab.rest.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Builder.Default
    List<Vaccine> vaccine = new ArrayList<>();
    @ElementCollection
            @Builder.Default
    List<String> imageUrl = new ArrayList<>();
}
