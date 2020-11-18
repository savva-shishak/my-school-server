package com.example.demo.students;

import com.example.demo.abstractcrud.Model;
import com.example.demo.groups.Group;
import com.example.demo.lessons.Lesson;

import javax.persistence.*;

@Entity
public class Student implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private Group group;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Group group) {
        this.name = name;
        this.group = group;
    }

    @Override
    public String getLessonLabel(Lesson l) {
        return group.getLessonLabel(l);
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
