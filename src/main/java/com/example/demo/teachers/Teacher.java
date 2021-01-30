package com.example.demo.teachers;

import com.example.demo.abstractcrud.Model;
import com.example.demo.lessons.Lesson;
import com.example.demo.subjects.Subject;
import com.example.demo.subjects.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tchr")
public class Teacher implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Cross.class)
    protected Long id;

    @JsonView(View.Cross.class)
    protected String name;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private Set<Subject> subjects;

    public Teacher() {
    }

    @Override
    public String getLessonLabel(Lesson l) {
        return String.format("%s (%s)", l.getGroup(), l.getRoom());
    }

    public Teacher(String name) {
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
