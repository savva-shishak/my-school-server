package com.example.demo.groups;

import com.example.demo.subjects.View;

public interface VGroup {
    interface JustList {}

    interface WidthSubjects extends JustList, View.Cross {}
}
