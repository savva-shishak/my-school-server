package com.example.demo.teachers;

import com.example.demo.abstractcrud.Model;
import com.example.demo.subjects.Subject;
import com.example.demo.subjects.SubjectsRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeachersRestController {

    @Autowired
    private SubjectsRepo subjectsRepo;

    @GetMapping("/{id}/subjects")
    @JsonView(Model.class)
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
}
