package com.krn.e_attendance.student.dto;

import jakarta.validation.constraints.NotBlank;

public class StudentRequestDTO {
	@NotBlank(message="Faculty is required")
	String faculty;
	
	@NotBlank(message="Name is required")
	String name;
	
	@NotBlank(message="batch is required")
	String batch;
	
	@NotBlank(message="Semester is required")
	String semester;
	
	@NotBlank(message="Roll number is required")
	String rollNo;
}
