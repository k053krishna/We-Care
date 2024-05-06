package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name="bookingtable")
@Data
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int booking_Id;
	private LocalDate date_of_birth;
	private String slot;
	
	 @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "coach_id")
	    private Coach coach;
}
