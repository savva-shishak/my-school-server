package com.example.demo.rooms;

import com.example.demo.pages.ListView;

public class ListViewImpl implements ListView {
    @Override
    public String getTitle() {
        return "Кабинеты";
    }

    @Override
    public String getLabelAll() {
        return "Все кабинеты";
    }

    @Override
    public String getAddLabel() {
        return "Новый кабинет";
    }
}
