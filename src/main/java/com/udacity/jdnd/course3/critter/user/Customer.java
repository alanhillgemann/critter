package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {

    @Column(length = 1000)
    private String notes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pet> pets;

    private String phoneNumber;

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public List<Pet> getPets() { return pets; }

    public void setPets(List<Pet> pets) { this.pets = pets; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void addPet(Pet pet) {
        if (pets == null) {
            List<Pet> pets = new ArrayList<>();
        }
        pets.add(pet);
    }
}
