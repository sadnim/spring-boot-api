package com.project.mooc.Controller;

import com.project.mooc.Entity.Lecturer;
import com.project.mooc.Repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecturers")
public class LecturerController {

    @Autowired
    LecturerRepository lecturerRepository;

    @GetMapping
    public ResponseEntity<List<Lecturer>> getAllLecturers() {
        List<Lecturer> lecturers = lecturerRepository.findAll();
        return ResponseEntity.ok(lecturers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable Long id) {
        return lecturerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Lecturer> createLecturer(@RequestBody Lecturer lecturer) {
        Lecturer createdLecturer = lecturerRepository.save(lecturer);
        return ResponseEntity.ok(createdLecturer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lecturer> updateLecturer(@PathVariable Long id, @RequestBody Lecturer updatedLecturer) {
        return lecturerRepository.findById(id)
                .map(existingLecturer -> {
                    existingLecturer.setOrganization(updatedLecturer.getOrganization());
                    existingLecturer.setLecturer(updatedLecturer.getLecturer());
                    // Set other fields as needed

                    Lecturer savedLecturer = lecturerRepository.save(existingLecturer);
                    return ResponseEntity.ok(savedLecturer);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecturer(@PathVariable Long id) {
        return lecturerRepository.findById(id)
                .map(existingLecturer -> {
                    lecturerRepository.delete(existingLecturer);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
