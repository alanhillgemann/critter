package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @Autowired
    private ScheduleService scheduleService;

    public ScheduleController(CustomerService customerService, EmployeeService employeeService, PetService petService, ScheduleService scheduleService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.petService = petService;
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertScheduleDTOToSchedule(scheduleDTO);
        List<Employee> employees = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();
        scheduleDTO.getEmployeeIds().forEach(employeeId -> employees.add(employeeService.findById(employeeId)));
        scheduleDTO.getPetIds().forEach(petId -> pets.add(petService.findById(petId)));
        Schedule savedSchedule = scheduleService.save(schedule, employees, pets);
        return convertScheduleToScheduleDTO(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.findAll();
        return convertSchedulesToScheduleDTOs(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = petService.findById(petId);
        List<Schedule> schedules = scheduleService.findAllByPet(pet);
        return convertSchedulesToScheduleDTOs(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        List<Schedule> schedules = scheduleService.findAllByEmployee(employee);
        return convertSchedulesToScheduleDTOs(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        Customer customer = customerService.findById(customerId);
        List<Pet> pets = petService.findAllByCustomer(customer);
        List<Schedule> schedules = scheduleService.findAllByPetsIn(pets);
        return convertSchedulesToScheduleDTOs(schedules);
    }

    private static Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }

    private static List<ScheduleDTO> convertSchedulesToScheduleDTOs(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        schedules.forEach(schedule -> scheduleDTOs.add(convertScheduleToScheduleDTO(schedule)));
        return scheduleDTOs;
    }

    private static ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        List<Long> employeeIds = new ArrayList<>();
        List<Long> petIds = new ArrayList<>();
        schedule.getEmployees().forEach(employee -> employeeIds.add(employee.getId()));
        schedule.getPets().forEach(pet -> petIds.add(pet.getId()));
        BeanUtils.copyProperties(schedule, scheduleDTO);
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }
}
