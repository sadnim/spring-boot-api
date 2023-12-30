package com.project.mooc.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "syllabuses")
public class Syllabus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long syllId;
	
	List<String> topics;
	
	@OneToOne(mappedBy = "syllabus")
	Course course;

	public Long getSyllId() {
		return syllId;
	}

	public void setSyllId(Long syllId) {
		this.syllId = syllId;
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
