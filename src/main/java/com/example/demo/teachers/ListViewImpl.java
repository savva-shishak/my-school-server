package com.example.demo.teachers;

import com.example.demo.pages.ListView;

class ListViewImpl implements ListView {
    @Override
    public String getTitle() {
        return "Учителя";
    }

    @Override
    public String getLabelAll() {
        return "Все учителя";
    }

    @Override
    public String getAddLabel() {
        return "Новый учитель";
    }
}
