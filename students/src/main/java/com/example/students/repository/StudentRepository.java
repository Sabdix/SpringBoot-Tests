package com.example.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.students.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
