package se331.lab.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String surname;
    Integer age;
    @OneToMany(mappedBy = "doctor")
            @Builder.Default
    List<Patients> patient = new ArrayList<>();
    @ElementCollection
    List<String> docImageUrl;
}
