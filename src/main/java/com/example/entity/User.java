package com.example.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Entity
@Table(name="usertable")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	@NotBlank(message = "Name cannot be blank")
	private String name;
	private String gender;
	private LocalDate date_of_birth=LocalDate.now();
	@NotBlank(message="password cannot be blank")
	private String password;
	private long mobile_number;
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @NotBlank(message = "Email cannot be blank")
	private String email;
	private String pincode;
	private String city;
	private String state;
	private String country;
	@OneToMany(mappedBy = "user")
	private List<Booking> booking;
}


