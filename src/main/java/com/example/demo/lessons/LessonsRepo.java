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
            "(" +
                "l.subject.teacher = s.teacher OR " +
                "l.group = :group OR " +
                "l.room = :room" +
            ")")
    ArrayList<Lesson> findCross(
            // @Param("subject")
                    Subject subject,
            @Param("group") Group group,
            @Param("room") Room room,
            @Param("day") int dayWeek,
            @Param("pair") int pair,
            @Param("id") long id
    );
}
