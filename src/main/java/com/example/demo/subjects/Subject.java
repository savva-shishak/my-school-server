package com.example.demo.subjects;

import com.example.demo.abstractcrud.Model;
import com.example.demo.groups.Group;
import com.example.demo.lessons.Lesson;
import com.example.demo.teachers.Teacher;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sbj")
public class Subject implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({View.Cross.class, Model.class})
    private Long id;

    @JsonView({View.Cross.class, Model.class})
    private String name;

    @JsonView(View.Cross.class)
    @ManyToOne
    private Teacher teacher;

    @JsonView(View.WithGroups.class)
    @ManyToMany
    @JoinTable(
            name = "subjects_groups",
            joinColumns = @JoinColumn(name = "subjects_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
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

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String getLessonLabel(Lesson l) {
        return String.format("%s (%s)", l.getGroup(), l.getRoom());
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return id.equals(subject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
