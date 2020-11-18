package com.example.demo.groups;

import com.example.demo.abstractcrud.Model;
import com.example.demo.lessons.Lesson;

import javax.persistence.*;

@Entity
@Table(name = "grp")
public class Group implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String name;

    public Group() {
    }

    @Override
    public String getLessonLabel(Lesson lesson) {
        return String.format("Учитель: %s (%s)", lesson.getTeacher(), lesson.getRoom());
    }

    public Group(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
