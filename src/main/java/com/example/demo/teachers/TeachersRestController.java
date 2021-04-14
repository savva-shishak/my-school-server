package com.example.demo.teachers;

import com.example.demo.abstractcrud.Model;
import com.example.demo.lessons.Lesson;
import com.example.demo.lessons.LessonsRepo;
import com.example.demo.subjects.Subject;
import com.example.demo.subjects.SubjectsRepo;
import com.example.demo.subjects.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeachersRestController {

    @Autowired
    private LessonsRepo lessonsRepo;

    @Autowired
    private SubjectsRepo subjectsRepo;

    @Autowired
    private TeachersRepo teachersRepo;

    @GetMapping("/{id}/subjects")
    @JsonView(View.WithGroups.class)
    public ArrayList<Subject> getSubjects(
            @PathVariable("id") Teacher teacher
    ) {
        return subjectsRepo.findByTeacher(teacher);
    }

    @PutMapping("/{teacher}/{name}")
    public Model addSubject(
            @PathVariable Teacher teacher,
            @PathVariable String name
    ) {
        Subject subject = new Subject(name, teacher);
        subjectsRepo.save(subject);
        return subject;
    }

    @GetMapping("/free")
    public List<Teacher> free(
            @RequestParam Integer dayWeek,
            @RequestParam Integer pairNum
    ) {
        ArrayList<Lesson> lessons = lessonsRepo.findByDayWeekAndPairNum(dayWeek, pairNum);

        List<Teacher> teachers = teachersRepo.findAll();

        for (Lesson lesson : lessons) {
            teachers.removeIf(teacher -> !lesson.getTeacher().id.equals(teacher.id));
        }

        return  teachers;
    }
}
