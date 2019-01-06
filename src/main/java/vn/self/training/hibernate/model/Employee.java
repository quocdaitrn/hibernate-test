package vn.self.training.hibernate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@AttributeOverride(name = "id", column = @Column(name = "employee_id"))
public class Employee extends AbstractEntity {

    private static final long serialVersionUID = 89273472304L;

    private String code;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Set<Project> projects = new HashSet<>();

    public Employee() {
        super();
    }

    public Employee(String code, String firstName, String lastName, Date birthDate) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Employee(String code, String firstName, String lastName, Date birthDate, Set<Project> projects) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.projects = projects;
    }

    @Column(nullable = false, length = 3)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthday) {
        this.birthDate = birthday;
    }

    @ManyToMany(mappedBy = "employees", cascade = { CascadeType.MERGE })
    @JsonBackReference
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
