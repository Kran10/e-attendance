package com.krn.e_attendance.student.repository;
import com.krn.e_attendance.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface StudentRepo extends JpaRepository<Student, Long>{
	Optional<Student>findByRollNumber(String rollNumber);
}
