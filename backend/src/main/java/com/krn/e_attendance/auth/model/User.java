package com.krn.e_attendance.auth.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Username is required")
	@Column(unique=true)
	private String username;
	
	@NotBlank(message="Email is required")
	@Email(message="Invalid email format")
	@Column(unique=true)
	private String email;
	

	@NotBlank(message="Password is required")
	private String password;
	
}
