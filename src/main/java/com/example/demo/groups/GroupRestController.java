package com.example.demo.groups;

import com.example.demo.lessons.Lesson;
import com.example.demo.lessons.LessonsRepo;
import com.example.demo.subjects.Subject;
import com.example.demo.subjects.SubjectsRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/api/my/groups")
public class GroupRestController {
    @Autowired
    private GroupsRepos repos;

    @Autowired
    private LessonsRepo lessonsRepo;

    @Autowired
    private SubjectsRepo subjectsRepo;

    @JsonView(VGroup.JustList.class)
    @GetMapping
    public Iterable<Group> findAll() {
        return repos.findAll();
    }

    @JsonView(VGroup.JustList.class)
    @PostMapping("/add")
    public Group add(
            @RequestBody String name
    ) {
        return repos.save(new Group(name));
    }

    @JsonView(VGroup.WidthSubjects.class)
    @GetMapping("/{id}")
    public GroupWithLessons getGroup(
            @PathVariable("id") Group group
    ) {
        Iterable<Lesson> lessons = lessonsRepo.findByGroup(group);
        return new GroupWithLessons(group, lessons);
    }

    @JsonView(VGroup.JustList.class)
    @GetMapping("/lessons/{id}")
    public Iterable<Lesson> getLessons(
            @PathVariable("id") Group group
    ) {
        return lessonsRepo.findByGroup(group);
    }

    @JsonView(VGroup.JustList.class)
    @PutMapping("/{id}")
    public void update(
            @PathVariable("id") Group group,
            @RequestBody String name
    ) {
        group.setName(name);
        repos.save(group);
    }

    @JsonView(VGroup.WidthSubjects.class)
    @GetMapping("/{group}/subjects/select")
    public Iterable<Subject> getSelectSubjects(
            @PathVariable Group group
    ) {
        return subjectsRepo.findByGroupsNotContaining(group);
    }

    @PutMapping("/subject/{group}/{subject}")
    public void addSubject(
            @PathVariable("group") Group group,
            @PathVariable("subject") Subject subject
    ) {
        group.getSubjects().add(subject);
        subject.getGroups().add(group);
        repos.save(group);
        subjectsRepo.save(subject);
    }

    @DeleteMapping("/subject/{group}/{subject}")
    public void deleteSubject(
            @PathVariable("group") Group group,
            @PathVariable("subject") Subject subject
    ) {
        for (Lesson l : lessonsRepo.findByGroupAndSubject(group, subject)) {
            lessonsRepo.delete(l);
        }

        group.getSubjects().removeIf(s -> s.getId().equals(subject.getId()));
        subject.getGroups().removeIf(g -> g.getId().equals(group.getId()));

        repos.save(group);
        subjectsRepo.save(subject);
    }
    
    @DeleteMapping("/{group}")
    public void delete(
            @PathVariable Group group
    ) {
        for (Subject subject : group.getSubjects()) {
            subject.getGroups().remove(group);
            subjectsRepo.save(subject);
        }

        group.setSubjects(new HashSet<>());
        repos.save(group);

        repos.delete(group);
    }

    class GroupWithLessons {
        @JsonView(VGroup.JustList.class)
        private Group group;
        @JsonView(VGroup.JustList.class)
        private Iterable<Lesson> lessons;

        public GroupWithLessons(Group group, Iterable<Lesson> lessons) {
            this.group = group;
            this.lessons = lessons;
        }

        public Group getGroup() {
            return group;
        }

        public Iterable<Lesson> getLessons() {
            return lessons;
        }
    }
}
