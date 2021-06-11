package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Customer extends User {

    @Column(length = 1000)
    private String notes;

    @OneToMany
    private List<Pet> pets;

    private String phoneNumber;
}
