package com.example.demo.rooms;

import com.example.demo.lessons.Lesson;
import com.example.demo.lessons.LessonsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoomsRestController {

    @Autowired
    private LessonsRepo lessonsRepo;

    @Autowired
    private RoomsRepo roomsRepo;

    @GetMapping("/free")
    public List<Room> free(
            @RequestParam Integer dayWeek,
            @RequestParam Integer pairNum
    ) {
        ArrayList<Lesson> lessons = lessonsRepo.findByDayWeekAndPairNum(dayWeek, pairNum);

        List<Room> rooms = roomsRepo.findAll();

        for (Lesson lesson : lessons) {
            rooms.removeIf(room -> !lesson.getRoom().id.equals(room.id));
        }

        return rooms;
    }
}
