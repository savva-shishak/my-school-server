package com.example.demo.lessons;

import com.example.demo.groups.Group;
import com.example.demo.rooms.Room;
import com.example.demo.teachers.Teacher;

import javax.persistence.*;

@Entity
@Table(name = "lsn")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Group group;

    @ManyToOne
    private Room room;

    private int dayWeek;

    private int pairNum;

    public Lesson() {
    }

    public Lesson(Teacher teacher, Group group, Room room, int dayWeek, int pairNum) {
        this.teacher = teacher;
        this.group = group;
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
        return String.format("%s - %s (%s)", group.getName(), teacher.getName(), room.getName());
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
