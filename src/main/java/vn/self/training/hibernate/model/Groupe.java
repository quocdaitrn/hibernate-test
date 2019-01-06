package vn.self.training.hibernate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groupe")
@AttributeOverride(name = "id", column = @Column(name = "groupe_id"))
public class Groupe extends AbstractEntity {

    private static final long serialVersionUID = 224357858656L;

    private Employee groupLeader;
    private Set<Project> projects = new HashSet<>();

    public Groupe() {
        super();
    }

    public Groupe(Employee groupLeader, Set<Project> projects) {
        this.groupLeader = groupLeader;
        this.projects = projects;
    }

    @OneToOne
    @JoinColumn(name = "groupe_leader_id")
    public Employee getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(Employee groupLeader) {
        this.groupLeader = groupLeader;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    @JsonBackReference
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
