package com.example.demo.groups;

import com.example.demo.abstractcrud.Model;
import com.example.demo.lessons.Lesson;
import com.example.demo.subjects.Subject;
import com.example.demo.subjects.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grp")
public class Group implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({View.Cross.class, VGroup.JustList.class})
    protected Long id;

    @JsonView({View.Cross.class, VGroup.JustList.class})
    protected String name;

    @JsonView(VGroup.WidthSubjects.class)
    @ManyToMany(mappedBy = "groups")
    private Set<Subject> subjects = new HashSet<>();

    public Group() {
    }

    @Override
    public String getLessonLabel(Lesson lesson) {
        return String.format("%s (%s)", lesson.getSubject(), lesson.getRoom());
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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return name;
    }
}
