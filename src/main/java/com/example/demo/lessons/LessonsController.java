package com.example.demo.lessons;

import com.example.demo.groups.Group;
import com.example.demo.groups.GroupsRepos;
import com.example.demo.rooms.Room;
import com.example.demo.rooms.RoomsRepo;
import com.example.demo.subjects.Subject;
import com.example.demo.teachers.Teacher;
import com.example.demo.teachers.TeachersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/lessons")
public class LessonsController {
    @Autowired
    private LessonsRepo lessonsRepo;

    @Autowired
    private GroupsRepos groupsRepos;

    @Autowired
    private TeachersRepo teachersRepo;

    @Autowired
    private RoomsRepo roomsRepo;

    @GetMapping("/new")
    public String createLesson(
            @RequestParam(name = "group", required = false) Group group,
            @RequestParam(name = "subject", required = false) Subject subject,
            @RequestParam(name = "room", required = false) Room room,
            @RequestParam("day") int dayWeek,
            @RequestParam("pair") int pairNum,
            @RequestParam(value = "back", required = false, defaultValue = "/") String backUrl,
            Model model
    ) {
        model.addAttribute("title", "Новое занятие");
        model.addAttribute("back", backUrl);

        fillModelForEdit(model, group, subject, room, dayWeek, pairNum);

        return "lesson";
    }

    @GetMapping("/edit/{id}")
    public String editLesson(
            @PathVariable("id") Lesson lesson, Model model,
            @RequestParam(value = "back", required = false, defaultValue = "/") String backUrl
    ) {
        model.addAttribute("title", "Редактировать занятие");
        model.addAttribute("back", backUrl);

        fillModelForEdit(model, new LessonHolder(lesson));

        return  "lesson";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam(name = "subject", required = false) Subject subject,
            @RequestParam(name = "group", required = false) Group group,
            @RequestParam int subgroup,
            @RequestParam int week,
            @RequestParam(name = "room", required = false) Room room,
            @RequestParam("day") int dayWeek,
            @RequestParam("pair") int pairNum,
            @RequestParam(value = "id", required = false, defaultValue = "-1") long id,
            @RequestParam(value = "back") String backUrl,
            Model model

    ) {
        Lesson lesson = new Lesson(subject, group, 0, room, week, dayWeek, pairNum);

        ArrayList<Lesson> cross = lessonsRepo.findCross(subject, group, subgroup, room, week, dayWeek, pairNum, id);

        if (cross.isEmpty()) {
            if (id != -1l) {
                lesson.setId(id);
            }

            lessonsRepo.save(lesson);

            return "redirect:" + backUrl;
        } else {
            model.addAttribute("Ошибка пересечения уроков");
            model.addAttribute("danger", "Нельзя поставить этот урок, т.к. он будет пересекать с другим(и) уроком(ми)");
            model.addAttribute("cross", cross);
            model.addAttribute("back", backUrl);
            fillModelForEdit(model, new LessonHolder(lesson));
            return "lesson";
        }
    }

    private void fillModelForEdit(Model model, Group group, Subject subject, Room room, int dayWeek, int pairNum) {
        LessonHolder holder = new LessonHolder(dayWeek, pairNum);

        if (group != null) holder.setGroup(group.getId());
        if (subject != null) holder.setSubject(subject.getId());
        if (room != null) holder.setRoom(room.getId());

        fillModelForEdit(model, holder);
    }

    private void fillModelForEdit(Model model, LessonHolder holder) {
        model.addAttribute("lesson", holder);

        model.addAttribute("groups", groupsRepos.findAll());
        model.addAttribute("teachers", teachersRepo.findAll());
        model.addAttribute("rooms", roomsRepo.findAll());
        model.addAttribute("week", Day.getWeek());
        model.addAttribute("pairs", new int[]{1, 2, 3, 4, 5, 6, 7});
    }
}
