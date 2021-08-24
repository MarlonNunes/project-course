package com.projectcourse.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Student extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idStudent;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();


}
