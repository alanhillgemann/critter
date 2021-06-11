package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.Schedule;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee extends User {

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany
    private Set<Schedule> schedules;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;
}
