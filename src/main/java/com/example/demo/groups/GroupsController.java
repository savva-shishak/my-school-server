package com.example.demo.groups;

import com.example.demo.abstractcrud.CrudController;
import com.example.demo.lessons.LessonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/groups")
public class GroupsController extends CrudController<Group, GroupsRepos> {
    @Autowired
    private LessonsService service;

    public GroupsController() {

        super("groups", Group::new, new ListViewImpl(), new InfoImpl());
    }
}
