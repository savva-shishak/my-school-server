package com.example.demo.teachers;

import com.example.demo.abstractcrud.CrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController extends CrudController<Teacher, TeachersRepo> {

    public TeacherController() {
        super("teachers", Teacher::new, new ListViewImpl(), new InfoImpl());
    }
}
