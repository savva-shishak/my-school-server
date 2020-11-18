package com.example.demo.abstractcrud;

import com.example.demo.lessons.Lesson;

public interface Model {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getLessonLabel(Lesson l);
}
