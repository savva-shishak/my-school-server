package com.example.demo.lessons;

class LessonHolder {
    private Long id;
    private Long group;
    private Long subject;
    private Long room;
    private int dayWeek;
    private int pairNum;

    public LessonHolder(Lesson lesson) {
        this(lesson.getDayWeek(), lesson.getPairNum());
        id = lesson.getId();
        group = lesson.getGroup().getId();
        subject = lesson.getSubject().getId();
        room = lesson.getRoom().getId();
    }

    public LessonHolder(int dayWeek, int pairNum) {
        this.dayWeek = dayWeek;
        this.pairNum = pairNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public Long getRoom() {
        return room;
    }

    public void setRoom(Long room) {
        this.room = room;
    }

    public int getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(int dayWeek) {
        this.dayWeek = dayWeek;
    }

    public int getPairNum() {
        return pairNum;
    }

    public void setPairNum(int pairNum) {
        this.pairNum = pairNum;
    }
}
