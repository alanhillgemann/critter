package com.udacity.jdnd.course3.critter.user;

import org.hibernate.annotations.Nationalized;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;
}
