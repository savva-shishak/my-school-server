package com.example.demo.groups;

import com.example.demo.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GroupsRepos extends JpaRepository<Group, Long> {
}
