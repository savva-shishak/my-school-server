package com.example.demo.subjects;

import com.example.demo.groups.Group;
import com.example.demo.teachers.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface SubjectsRepo extends JpaRepository<Subject, Long> {
    ArrayList<Subject> findByTeacher(Teacher teacher);
    ArrayList<Subject> findByGroupsContaining(Group group);
    ArrayList<Subject> findByGroupsNotContaining(Group group);
    ArrayList<Subject> findByTeacherAndGroupsNotContaining(Teacher teacher, Group group);
}

