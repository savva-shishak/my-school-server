package com.example.demo.subjects;

import com.example.demo.teachers.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface SubjectsRepo extends JpaRepository<Subject, Long> {
    ArrayList<Subject> findByTeacher(Teacher teacher);
}
