package org.hbdev.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hbdev.enums.Gender;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Gender gender;
    private String cin;
    private LocalDate birthDate;
}
