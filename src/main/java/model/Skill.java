package model;

import java.io.Serializable;

public class Skill implements Serializable {
    private Integer id;
    private String name;
    private Status status;


    public Skill(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.status = Status.ACTIVE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
