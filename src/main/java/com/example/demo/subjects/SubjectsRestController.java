package com.example.demo.subjects;

import com.example.demo.groups.Group;
import com.example.demo.teachers.Teacher;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/subjects")
public class SubjectsRestController {

    @Autowired
    private SubjectsRepo repo;

    @GetMapping
    @JsonView(View.JustJson.class)
    public ArrayList<Subject> getSubjectsByTeacher(
            @RequestParam Teacher teacher,
            @RequestParam Group group
    ) {
        return repo.findByTeacherAndGroupsNotContaining(teacher, group);
    }
}
