package com.example.demo.subjects;

import com.example.demo.teachers.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subjects")
public class SubjectsController {

    @Autowired
    private SubjectsRepo repo;

    @PostMapping("/add")
    public String createSubject(
            @RequestParam Teacher teacher,
            @RequestParam String name
    ) {
        repo.save(new Subject(name, teacher));
        return "redirect:/teachers/" + teacher.getId();
    }

    @GetMapping("/delete/{subject}")
    public String delete(
            @PathVariable Subject subject
    ) {
        long id = subject.getTeacher().getId();
        repo.delete(subject);

        return "redirect:/teachers/" + id;
    }
}
