package com.example.demo.groups;

import com.example.demo.pages.ListView;

class ListViewImpl implements ListView {

    @Override
    public String getTitle() {
        return "Группы";
    }

    @Override
    public String getLabelAll() {
        return "Все группы";
    }

    @Override
    public String getAddLabel() {
        return "Новая группа";
    }
}
