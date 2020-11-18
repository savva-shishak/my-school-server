package com.example.demo.lessons;

import java.util.ArrayList;

public class Day {
    private String name;
    private ArrayList<Lesson> lessons = new ArrayList<>();

    public Day(String name, int dayWeek, ArrayList<Lesson> lessons) {

        this.name = name;

        for (int i = 0; i < 7; i++) {
            this.lessons.add(new Lesson(dayWeek, i + 1));
        }

        for (Lesson lesson : lessons) {
            if (lesson.getDayWeek() == dayWeek) {
                this.lessons.set(lesson.getPairNum() - 1, lesson);
            }
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    static class DayModel {
        protected int id;

        protected String name;

        public DayModel(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static ArrayList<DayModel> getWeek() {
        return new ArrayList<>() {{
            add(new DayModel(1, "Понедельник"));
            add(new DayModel(2, "Вторник"));
            add(new DayModel(3, "Среда"));
            add(new DayModel(4, "Четверг"));
            add(new DayModel(5, "Пятница"));
            add(new DayModel(6, "Суббота"));
        }};
    }
}
