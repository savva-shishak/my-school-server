package com.example.demo.groups;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepos extends JpaRepository<Group, Long> {
}


