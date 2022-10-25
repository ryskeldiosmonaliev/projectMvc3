package peaksoft.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;



@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "grup name should not be empty")
    @Size(min = 2, max = 30, message = "grup name should be between 2 and 30 characters")
    @Column(name = "group_name")
    private String groupName;

    @Column(name = "date_of_start")
    private LocalDate dateOfStart;

    @Column(name = "date_of_finish")
    private  LocalDate dateOfFinish;
}
