package com.example.demo.subjects;

import com.example.demo.abstractcrud.Model;
import com.example.demo.groups.Group;
import com.example.demo.lessons.Lesson;
import com.example.demo.teachers.Teacher;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "sbj")
public class Subject implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private Teacher teacher;

    @OneToMany
    private ArrayList<Group> groups;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String getLessonLabel(Lesson l) {
        return String.format("%s (%s)", l.getGroup(), l.getRoom());
    }
}
