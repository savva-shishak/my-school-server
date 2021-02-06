package com.example.demo.lessons;

import com.example.demo.groups.Group;
import com.example.demo.rooms.Room;
import com.example.demo.subjects.Subject;
import com.example.demo.subjects.View;
import com.example.demo.teachers.Teacher;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "lsn")
public class Lesson {

    @JsonView(View.Cross.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonView(View.Cross.class)
    @ManyToOne
    private Subject subject;

    @JsonView(View.Cross.class)
    @ManyToOne
    private Group group;

    @JsonView(View.Cross.class)
    private Integer subGroup;

    @JsonView(View.Cross.class)
    @ManyToOne
    private Room room;

    @JsonView(View.Cross.class)
    private int dayWeek;

    @JsonView(View.Cross.class)
    private int pairNum;

    public Lesson() {
    }

    public Lesson(Subject subject, Group group, Integer subGroup, Room room, int dayWeek, int pairNum) {
        this.subject = subject;
        this.group = group;
        this.subGroup = subGroup;
        this.room = room;
        this.dayWeek = dayWeek;
        this.pairNum = pairNum;
    }

    public Lesson(int dayWeek, int pairNum) {
        this.dayWeek = dayWeek;
        this.pairNum = pairNum;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", group.getName(), subject.getName(), room.getName());
    }

    public String getLinkCreate(String entities, long id) {
        String entity = entities.substring(0, entities.length() - 1);
        return String.format("/lessons/new?%s=%d&day=%d&pair=%d", entity, id, dayWeek, pairNum);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(int dayWeek) {
        this.dayWeek = dayWeek;
    }

    public int getPairNum() {
        return pairNum;
    }

    public void setPairNum(int pairNum) {
        this.pairNum = pairNum;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return subject.getTeacher();
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(Integer subGroup) {
        this.subGroup = subGroup;
    }
}
