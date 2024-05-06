
package com.example.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "coachtable")
@Data
public class Coach {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "coach_id")
	private int coachId;
	private String name;
	private String gender;
	private LocalDate date_of_birth=LocalDate.now();;
	private String password;
	private long mobile_number;
	private String speciality;
	@OneToMany(mappedBy = "coach")
	private List<Booking> booking;

}
