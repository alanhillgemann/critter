package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetService petService;

    public PetController(PetService petService, CustomerService customerService) {
        this.customerService = customerService;
        this.petService = petService;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pets = petService.findById(petId);
        return convertPetToPetDTO(pets);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAll();
        return convertPetsToPetDTOs(pets);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        Customer customer = customerService.findById(ownerId);
        List<Pet> pets = petService.findAllByCustomer(customer);
        return convertPetsToPetDTOs(pets);
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDTOToPet(petDTO);
        Long customerId = petDTO.getOwnerId();
        Customer customer = customerService.findById(customerId);
        Pet savedPet = petService.save(pet, customer);
        return convertPetToPetDTO(savedPet);
    }

    private static Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    private static List<PetDTO> convertPetsToPetDTOs(List<Pet> pets) {
        List<PetDTO> petDTOs = new ArrayList<>();
        pets.forEach(pet -> petDTOs.add(convertPetToPetDTO(pet)));
        return petDTOs;
    }

    private static PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }
}
