package com.krn.e_attendance.student.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentResponseDTO {
	private Long id;
	private String name;
	private String faculty;
	private String batch;
	private String semester;
	private String rollNumber;
}
