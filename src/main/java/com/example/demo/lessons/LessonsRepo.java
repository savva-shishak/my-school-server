package com.example.demo.lessons;

import com.example.demo.groups.Group;
import com.example.demo.rooms.Room;
import com.example.demo.subjects.Subject;
import com.example.demo.teachers.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LessonsRepo extends JpaRepository<Lesson, Long> {
    @Query("FROM Lesson l WHERE l.subject.teacher = :teacher")
    ArrayList<Lesson> findByTeacher(Teacher teacher);
    ArrayList<Lesson> findByGroup(Group group);
    ArrayList<Lesson> findByRoom(Room room);

    @Query("FROM Lesson l " +
            "JOIN Subject s ON s = :subject " +
            "WHERE " +
            "l.id <> :id AND " +
            "l.dayWeek = :day AND " +
            "l.pairNum = :pair AND " +
            "(l.week = 0 OR :week = 0 OR l.week = :week) AND " +
            "(" +
                "l.subject.teacher = s.teacher OR " +
                "(l.group = :group AND (l.subGroup = :subgroup OR :subgroup = 0 OR l.subGroup = 0)) OR " +
                "l.room = :room" +
            ")")
    ArrayList<Lesson> findCross(
            @Param("subject") Subject subject,
            @Param("group") Group group,
            @Param("subgroup") int subGroup,
            @Param("room") Room room,
            @Param("week") int week,
            @Param("day") int dayWeek,
            @Param("pair") int pair,
            @Param("id") long id
    );

    ArrayList<Lesson> findByGroupAndSubject(Group group, Subject subject);

    ArrayList<Lesson> findByDayWeekAndPairNum(int dayWeek, int pairNum);
}
