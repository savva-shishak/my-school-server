package com.example.demo.lessons;

import com.example.demo.groups.Group;
import com.example.demo.rooms.Room;
import com.example.demo.subjects.Subject;
import com.example.demo.subjects.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/lessons")
public class LessonsRestController {
    @Autowired
    private LessonsRepo repo;

    @JsonView(View.Cross.class)
    @GetMapping("/cross")
    public ArrayList<Lesson> getCross(
            @RequestParam(required = false) Group group,
            @RequestParam(required = false) Subject subject,
            @RequestParam(required = false) Room room,
            @RequestParam(required = false) int dayWeek,
            @RequestParam(required = false) int pairNum
    ) {
        return repo.findCross(subject, group, room, dayWeek, pairNum, -1);
    }
}
