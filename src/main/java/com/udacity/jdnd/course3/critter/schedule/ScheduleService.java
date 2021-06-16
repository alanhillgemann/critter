package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findAllByEmployee(Employee employee) {
        return scheduleRepository.findAllByEmployees(employee);
    }

    public List<Schedule> findAllByPet(Pet pet) {
        return scheduleRepository.findAllByPets(pet);
    }

    public List<Schedule> findAllByPetsIn(List<Pet> pets) {
        return scheduleRepository.findAllByPetsIn(pets);
    }

    public Schedule save(Schedule schedule, List<Employee> employees, List<Pet> pets) {
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        employees.forEach(employee -> {
            employee.addSchedule(savedSchedule);
        });
        pets.forEach(pet -> {
            pet.addSchedule(savedSchedule);
        });
        return savedSchedule;
    }
}
