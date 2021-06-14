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
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public List<Pet> findAllByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer " + customerId + " not found"));
        return petRepository.findAllByCustomer_Id(customerId);
    }

    public Pet findById(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("Pet " + petId + " not found"));
        return pet;
    }

    public Pet save(Pet pet, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer " + customerId + " not found"));
        pet.setCustomer(customer);
        Pet savedPet = petRepository.save(pet);
        customer.addPet(savedPet);
        return savedPet;
    }
}
