package peaksoft.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "cours name should not be empty")
    @Size(min = 2, max = 30, message = "cours name should be between 2 and 30 characters")
    @Column(name = "course_name")
    private String courseName;

    @NotEmpty(message = "month should not be empty")
    @Size(min = 2, max = 30, message = "month should be between 2 and 30 characters")
    @Column(name = "duration_month")
    private String durationMonth;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "company_id")
    private Company company;


    @Transient
    private Long companyId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "groups_courses", joinColumns = @JoinColumn(name = "courses_id")
            , inverseJoinColumns = @JoinColumn(name = "groups_id"))
    private List<Group> groups;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "teachers_courses",joinColumns = @JoinColumn(name = "courses_id")
            , inverseJoinColumns = @JoinColumn(name = "teachers_id"))
    private Teacher teacher;
}
