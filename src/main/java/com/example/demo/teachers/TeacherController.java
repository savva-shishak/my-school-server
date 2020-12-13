package com.example.demo.teachers;

import com.example.demo.abstractcrud.CrudController;
import com.example.demo.subjects.SubjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController extends CrudController<Teacher, TeachersRepo> {

    @Autowired
    private SubjectsRepo subjectsRepo;

    public TeacherController() {
        super("teachers", Teacher::new, new ListViewImpl(), new InfoImpl());
    }

    @Override
    public void setInfoPropsToModel(Model model, Teacher item) {
        model.addAttribute("subjects", subjectsRepo.findByTeacher(item));
    }

    @Override
    protected String getInfoPage() {
        return "teachers/info";
    }


}
