package com.example.demo.lessons;

import com.example.demo.groups.Group;
import com.example.demo.groups.GroupsRepos;
import com.example.demo.lessons.exceptions.NoSelectEntityException;
import com.example.demo.rooms.Room;
import com.example.demo.rooms.RoomsRepo;
import com.example.demo.subjects.Subject;
import com.example.demo.subjects.View;
import com.example.demo.teachers.TeachersRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/lessons")
public class LessonsRestController {
    @Autowired
    private LessonsRepo repo;

    @Autowired
    private GroupsRepos groupsRepos;

    @Autowired
    private TeachersRepo teachersRepo;

    @Autowired
    private RoomsRepo roomsRepo;

    @JsonView(View.Cross.class)
    @GetMapping("/cross")
    public ArrayList<Lesson> getCross(
            @RequestParam Group group,
            @RequestParam Subject subject,
            @RequestParam Room room,
            @RequestParam Integer dayWeek,
            @RequestParam Integer pairNum
    ) {
        return repo.findCross(subject, group, room, dayWeek, pairNum, -1);
    }

    @JsonView(View.Cross.class)
    @GetMapping("/{entity}/{entityId}")
    public Iterable<Lesson> getForEntity(
            @PathVariable("entity") String entity,
            @PathVariable("entityId") Long id
    ) {
        switch (entity) {
            case "group":
                return repo.findByGroup(groupsRepos.getOne(id));
            case "teacher":
                return repo.findByTeacher(teachersRepo.getOne(id));
            case "room":
                return repo.findByRoom(roomsRepo.getOne(id));
            default:
                throw new NoSelectEntityException(entity);
        }
    }

    @GetMapping
    public void createLesson(
            @RequestParam Group group,
            @RequestParam Subject subject,
            @RequestParam Room room,
            @RequestParam Integer dayWeek,
            @RequestParam Integer pairNum
    ) {
        repo.save(new Lesson(subject, group, room, dayWeek, pairNum));
    }

    @DeleteMapping("/{lesson}")
    public void removeLesson(
            @PathVariable Lesson lesson
    ) {
        repo.delete(lesson);
    }
}
