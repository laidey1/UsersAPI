package com.tts.UsersAPI.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
	
	@Max(value =20, message = " first name cannot exceed 20 characters")
	private String firstName;
	@Min(value= 2, message = " last name must have at least 2 characters")
	private String lastName;
	@Length(min=4, message ="State must have at least 4 characters" )
	@Length(max= 20, message = "State cannot exceed 20 characters")
	private String state;
	
	public User(String firstName, String lastName, String state) {
		this.firstName =firstName;
		this.lastName = lastName;
		this.state = state;
	}
	}

	
	
	
	
	
	
	


