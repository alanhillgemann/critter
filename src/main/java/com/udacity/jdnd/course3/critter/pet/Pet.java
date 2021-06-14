package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate birthDate;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false,
            cascade = CascadeType.ALL
    )
    private Customer customer;

    @Nationalized
    private String name;

    @Column(length = 1000)
    private String notes;

    @ManyToMany
    private Set<Schedule> schedules;

    @Enumerated(EnumType.STRING)
    private PetType type;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDate getBirthDate() { return birthDate; }

    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    // Schedules

    public PetType getType() { return type; }

    public void setType(PetType type) { this.type = type; }
}
