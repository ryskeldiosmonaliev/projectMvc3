package peaksoft.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;



@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "fist name should not be empty")
    @Size(min = 2, max = 30, message = " first name should be between 2 and 30 characters")
    @Column(name = "teacher_firt_name")
    private String firstName;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(name = "teacher_email")
    private String email;


    @NotEmpty(message = "last name should not be empty")
    @Size(min = 2, max = 30, message = " last name should be between 2 and 30 characters")
    @Column(name = "teacg=her_last_name")
    private String lastName;
}
