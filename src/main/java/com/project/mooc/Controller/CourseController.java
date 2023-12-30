package com.project.mooc.Controller;

import com.project.mooc.Entity.Course;
import com.project.mooc.Entity.Lecturer;
import com.project.mooc.Entity.Student;
import com.project.mooc.Entity.Syllabus;
import com.project.mooc.Repository.CourseRepository;
import com.project.mooc.Repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    
	@Autowired
    CourseRepository courseRepository;
	@Autowired
    StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getStudentsByCourseId(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(course -> ResponseEntity.ok(course.getStudents()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/syllabus")
    public ResponseEntity<Syllabus> getSyllabusByCourseId(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(course -> ResponseEntity.ok(course.getSyllabus()))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}/lecturer")
    public ResponseEntity<Lecturer> getLecturerByCourseId(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(course -> ResponseEntity.ok(course.getLecturer()))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseRepository.save(course);
        return ResponseEntity.ok(createdCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setCourseTitle(updatedCourse.getCourseTitle());
                    // Set other fields as needed

                    Course savedCourse = courseRepository.save(existingCourse);
                    return ResponseEntity.ok(savedCourse);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    courseRepository.delete(existingCourse);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
