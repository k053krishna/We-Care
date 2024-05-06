package com.example.payload;

import java.time.LocalDate;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserProfileDto {

	private int user_Id;
	private String name;
	private String gender;
	private LocalDate date_of_birth=LocalDate.now();
	private String password;
	private long mobile_number;
	private String email;
	private String pincode;
	private String city;
	private String state;
	private String country;
}
