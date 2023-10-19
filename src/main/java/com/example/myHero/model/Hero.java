package com.example.myHero.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "Hero")
public class Hero {

    @Id
    @SequenceGenerator(
            name = "hero_sequence",
            sequenceName = "hero_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hero_sequence"
    )
    private long id;
    private String name;
    private String description;
    private LocalDate dob;

    @Transient
    private Integer age;

    public Hero() {
    }

    public Hero(String name, String description, LocalDate dob) {
        this.name = name;
        this.description = description;
        this.dob = dob;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
