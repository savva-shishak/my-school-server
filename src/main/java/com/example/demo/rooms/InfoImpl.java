package com.example.demo.rooms;

import com.example.demo.pages.Info;

public class InfoImpl implements Info {
    @Override
    public String getPlaceholder() {
        return "Название кабинета";
    }
}
