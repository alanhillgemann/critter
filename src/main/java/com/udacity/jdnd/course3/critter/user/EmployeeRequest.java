package com.udacity.jdnd.course3.critter.user;

import java.time.LocalDate;
import java.util.Set;

public class EmployeeRequest {

    private LocalDate date;

    private Set<EmployeeSkill> skills;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }
}
