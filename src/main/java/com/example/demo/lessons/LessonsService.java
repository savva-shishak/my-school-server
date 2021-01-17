package com.example.demo.lessons;

import com.example.demo.abstractcrud.Model;
import com.example.demo.groups.Group;
import com.example.demo.lessons.exceptions.NoSelectEntityException;
import com.example.demo.rooms.Room;
import com.example.demo.teachers.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LessonsService {
    @Autowired
    private LessonsRepo repo;

    public ArrayList<Day> getBy(String nameModel, Model model) {
        switch (nameModel) {
            case "groups":
            case "group":
                return getWeek(repo.findByGroup((Group) model));
            case "teachers":
            case "teacher":
                return getWeek(repo.findByTeacher((Teacher) model));
            case "rooms":
            case "room":
                return getWeek(repo.findByRoom((Room) model));
            default:
                throw new NoSelectEntityException(nameModel);
        }
    }

    public ArrayList<Day> getLessonsOfTeacher(Teacher teacher) {
        ArrayList<Lesson> lessons = repo.findByTeacher(teacher);

        return getWeek(lessons);
    }

    public ArrayList<Day> getLessonsOfGroup(Group group) {
        ArrayList<Lesson> lessons = repo.findByGroup(group);

        return getWeek(lessons);
    }

    public ArrayList<Day> getLessonsOfRoom(Room room) {
        ArrayList<Lesson> lessons = repo.findByRoom(room);

        return getWeek(lessons);
    }

    public ArrayList<Day> getWeek(ArrayList<Lesson> lessons) {
        return new ArrayList<>() {{
            add(new Day("Понедельник", 1, lessons));
            add(new Day("Вторник", 2, lessons));
            add(new Day("Среда", 3, lessons));
            add(new Day("Четверг", 4, lessons));
            add(new Day("Пятница", 5, lessons));
            add(new Day("Суббота", 6, lessons));
        }};
    }

}
