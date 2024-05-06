package com.example.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiResponse {


	private String message;
	private boolean sucess;
}
