package com.example.demo.configs;

import com.example.demo.groups.Group;
import com.example.demo.rooms.Room;
import com.example.demo.teachers.Teacher;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RestConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Group.class);
        config.exposeIdsFor(Teacher.class);
        config.exposeIdsFor(Room.class);
    }
}
