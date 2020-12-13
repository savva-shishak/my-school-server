package com.example.demo.groups;

import com.example.demo.abstractcrud.CrudController;
import com.example.demo.rooms.RoomsRepo;
import com.example.demo.subjects.Subject;
import com.example.demo.subjects.SubjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/groups")
public class GroupsController extends CrudController<Group, GroupsRepos> {
    @Autowired
    private SubjectsRepo subjectsRepo;

    @Autowired
    private RoomsRepo roomsRepo;

    public GroupsController() {

        super("groups", Group::new, new ListViewImpl(), new InfoImpl());
    }

    @Override
    public void setInfoPropsToModel(Model model, Group item) {
        model.addAttribute("otherSubjects", subjectsRepo.findByGroupsNotContaining(item));
        model.addAttribute("subjects", subjectsRepo.findByGroupsContaining(item));
        model.addAttribute("rooms", roomsRepo.findAll());
    }

    @Override
    protected String getInfoPage() {
        return "groups/info";
    }

    @PostMapping("/add-subject/{id}")
    public String addSubject(
            @PathVariable("id") Group group,
            @RequestParam Subject subject
    ) {
        subject.getGroups().add(group);
        subjectsRepo.save(subject);
        return "redirect:/groups/" + group.id;
    }

    @GetMapping("/remove-subject")
    public String removeSubject(
            @RequestParam Group group,
            @RequestParam Subject subject
    ) {
        subject.getGroups().remove(group);
        subjectsRepo.save(subject);
        return "redirect:/groups/" + group.id;
    }
}
