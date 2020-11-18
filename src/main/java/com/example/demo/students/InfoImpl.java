package com.example.demo.students;

import com.example.demo.pages.Info;

public class InfoImpl implements Info {
    @Override
    public String getPlaceholder() {
        return "Имя студента";
    }
}
