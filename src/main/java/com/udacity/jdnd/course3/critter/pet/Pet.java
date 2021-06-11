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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Customer customer;

    @Nationalized
    private String name;

    @Column(length = 1000)
    private String notes;

    @ManyToMany(mappedBy = "pets")
    private Set<Schedule> schedules;

    @Enumerated(EnumType.STRING)
    private PetType type;
}
