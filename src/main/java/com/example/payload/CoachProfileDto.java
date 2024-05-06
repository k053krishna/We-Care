package com.example.payload;

import java.time.LocalDate;

import lombok.Data;
@Data
public class CoachProfileDto {

	private int coach_Id;
	private String name;
	private String gender;
	private LocalDate date_of_birth;
	private String password;
	private long mobile_number;
	private String speciality;
}
