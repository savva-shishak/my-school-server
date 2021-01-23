package com.example.demo.teachers;

import com.example.demo.groups.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TeachersRepo extends JpaRepository<Teacher, Long> {
}
