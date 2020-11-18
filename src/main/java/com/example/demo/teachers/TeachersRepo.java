package com.example.demo.teachers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepo extends JpaRepository<Teacher, Long> {
}
