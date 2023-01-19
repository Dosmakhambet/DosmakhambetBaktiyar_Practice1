package model;

import java.io.Serializable;
import java.util.List;

public class Developer implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private Specialty specialty;
    private List<Skill> skills;
    private Status status;

    public Developer(Integer id, String firstName, String lastName, Specialty specialty, List<Skill> skills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.skills = skills;
        this.status = Status.ACTIVE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String str = "Developer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty=" + specialty.toString() +
                ", skills=[";

        for(Skill a : skills){
            str += a.toString() + "\n";
        };
         str += "]}";
         return str;
    }
}
