package com.example.demo.students;

import com.example.demo.pages.ListView;

public class ListViewImpl implements ListView {
    @Override
    public String getTitle() {
        return "Студенты";
    }

    @Override
    public String getLabelAll() {
        return "Все студенты";
    }

    @Override
    public String getAddLabel() {
        return "Новый студент";
    }
}
