package com.example.demo.students;

import com.example.demo.abstractcrud.CrudController;
import com.example.demo.groups.Group;
import com.example.demo.groups.GroupsRepos;
import com.example.demo.lessons.Day;
import com.example.demo.lessons.LessonsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("students")
public class StudentsController extends CrudController<Student, StudentsRepo> {

    @Autowired
    private LessonsRepo lessonsRepo;

    @Autowired
    private GroupsRepos groupsRepos;

    public StudentsController() {
        super("students", Student::new, new ListViewImpl(), new InfoImpl());
    }

    @Override
    protected ArrayList<Day> getWeek(Student item) {
        return lessonsService.getWeek(lessonsRepo.findByGroup(item.getGroup()));
    }

    @Override
    protected String getListPageNameAndSetAttrs(Model model) {
        model.addAttribute("groups", groupsRepos.findAll());
        return "students/list";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("group") Group group,
            @RequestParam("name") String name

    ) {
        repo.save(new Student(name, group));
        return "redirect:/students";
    }
}
