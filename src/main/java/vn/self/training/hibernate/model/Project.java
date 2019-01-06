package vn.self.training.hibernate.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
@AttributeOverride(name = "id", column = @Column(name = "project_id"))
public class Project extends AbstractEntity {

    private static final long serialVersionUID = 784209349893L;

    private int projectNumber;
    private String name;
    private String customer;
    private String status;
    private Date startDate;
    private Date endDate;
    private Set<Employee> employees = new HashSet<Employee>();
    private Groupe group;

    public Project() {
        super();
    }

    public Project(int projectNumber, String name) {
        this.projectNumber = projectNumber;
        this.name = name;
    }

    public Project(int projectNumber, String name, String customer, String status, Date startDate, Date endDate,
                   Set<Employee> employee, Groupe group) {
        this.projectNumber = projectNumber;
        this.name = name;
        this.customer = customer;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employees = employee;
        this.group = group;
    }

    @Column(name = "project_number", unique = true)
    public int getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "start_Date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_Date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "project_employee", joinColumns = {
            @JoinColumn(name = "project_id", referencedColumnName = "project_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id") })
    @JsonManagedReference
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employee) {
        this.employees = employee;
    }

    @ManyToOne
    @JoinColumn(name = "groupe_id")
    @JsonManagedReference
    public Groupe getGroup() {
        return group;
    }

    public void setGroup(Groupe group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectNumber=" + projectNumber +
                ", name='" + name + '\'' +
                ", customer='" + customer + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", employees=" + employees +
                ", group=" + group +
                '}';
    }
}