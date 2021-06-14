package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
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

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = findById(employeeId);
        employee.setDaysAvailable(daysAvailable);
    }
}
