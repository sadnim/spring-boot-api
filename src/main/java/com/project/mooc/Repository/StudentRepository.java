package com.project.mooc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mooc.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
