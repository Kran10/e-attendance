package com.krn.e_attendance.student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name="students")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message="Student name is required")
	private String name;
	
	@NotBlank(message="Faculty is required")
	private String faculty;
	
	@NotBlank(message="Batch is required")
	String batch;
	
	@NotBlank(message="Semester is required")
	private String semester;
	
	@NotBlank(message="Roll number is required")
	@Column(unique=true)
	private String rollNumber;
	
	public Student(String name, String faculty, String batch, String semester, String rollNumber) {
		this.name=name;
		this.faculty=faculty;
		this.batch=batch;
		this.semester=semester;
		this.rollNumber=rollNumber;
		
	}

}
