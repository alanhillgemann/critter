package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee " + employeeId + " not found"));
        return employee;
    }

    public List<Employee> findEmployeesForService(EmployeeRequest employeeRequest) {
        DayOfWeek date = employeeRequest.getDate().getDayOfWeek();
        Set<EmployeeSkill> skills = employeeRequest.getSkills();
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> employeesForService = new ArrayList<>();
        employees.forEach(employee -> {
            if (employee.getDaysAvailable().contains(date) && employee.getSkills().containsAll(skills)) {
                employeesForService.add(employee);
            }
        });
        return employeesForService;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = findById(employeeId);
        employee.setDaysAvailable(daysAvailable);
    }
}
