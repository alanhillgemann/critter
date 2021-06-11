package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;

    private LocalDate date;

    @ManyToMany
    private List<Employee> employees;

    @ManyToMany
    private List<Pet> pets;
}
