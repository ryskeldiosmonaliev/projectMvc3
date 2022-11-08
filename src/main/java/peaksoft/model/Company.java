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
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "company name should not be empty")
    @Size(min = 2, max = 30, message = "company name should be between 2 and 30 characters")
    @Column(name = "company_name")
    private String companyName;

    @NotEmpty(message = "coountry should not be empty")
    @Size(min = 2, max = 30, message = "country should be between 2 and 30 characters")
    @Column(name = "located_country")
    private String locatedCountry;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},mappedBy = "company")
    private List<Course>courses;
}
