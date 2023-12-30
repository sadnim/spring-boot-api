package com.project.mooc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mooc.Entity.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Long>{

}
