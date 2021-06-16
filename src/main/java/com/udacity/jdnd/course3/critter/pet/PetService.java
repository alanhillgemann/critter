package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public List<Pet> findAllByCustomer(Customer customer) {
        List<Pet> pets = petRepository.findAllByCustomer(customer);
        return pets;
    }

    public Pet findById(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("Pet " + petId + " not found"));
        return pet;
    }

    public Pet save(Pet pet, Customer customer) {
        pet.setCustomer(customer);
        Pet savedPet = petRepository.save(pet);
        customer.addPet(savedPet);
        return savedPet;
    }
}
