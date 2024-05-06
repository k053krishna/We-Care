package com.example.payload;

import java.time.LocalDate;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingDto {
	
	private LocalDate date_of_birth=LocalDate.now();
	private String slot;
}
