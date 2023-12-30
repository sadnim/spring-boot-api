package com.project.mooc.Controller;

import com.project.mooc.Entity.Syllabus;
import com.project.mooc.Repository.SyllabusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/syllabuses")
public class SyllabusController {
 
    @Autowired
    SyllabusRepository syllabusRepository;

    @GetMapping
    public ResponseEntity<List<Syllabus>> getAllSyllabuses() {
        List<Syllabus> syllabuses = syllabusRepository.findAll();
        return ResponseEntity.ok(syllabuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Syllabus> getSyllabusById(@PathVariable Long id) {
        return syllabusRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Syllabus> createSyllabus(@RequestBody Syllabus syllabus) {
        Syllabus createdSyllabus = syllabusRepository.save(syllabus);
        return ResponseEntity.ok(createdSyllabus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Syllabus> updateSyllabus(@PathVariable Long id, @RequestBody Syllabus updatedSyllabus) {
        return syllabusRepository.findById(id)
                .map(existingSyllabus -> {
                    existingSyllabus.setTopics(updatedSyllabus.getTopics());
                    // Set other fields as needed

                    Syllabus savedSyllabus = syllabusRepository.save(existingSyllabus);
                    return ResponseEntity.ok(savedSyllabus);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSyllabus(@PathVariable Long id) {
        return syllabusRepository.findById(id)
                .map(existingSyllabus -> {
                    syllabusRepository.delete(existingSyllabus);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
