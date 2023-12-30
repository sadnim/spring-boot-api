package com.project.mooc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mooc.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
