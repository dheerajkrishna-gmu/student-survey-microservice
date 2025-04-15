package com.assginment.swe645.studentSurveyMicroservice.Entities;

import java.time.LocalDate;
import java.util.List;

import com.assginment.swe645.studentSurveyMicroservice.Enums.InterestSource;
import com.assginment.swe645.studentSurveyMicroservice.Enums.LikedMostOption;
import com.assginment.swe645.studentSurveyMicroservice.Enums.RecommendLikelihood;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "First name is required")
	private String firstName;

	@NotBlank(message = "Last name is required")
	private String lastName;

	@NotBlank(message = "Street address is required")
	private String streetAddress;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;

	/*
	 * @Pattern(regexp = "d{5}", message = "Invalid ZIP code")
	 */
	private String zip;

	@NotBlank(message = "Telephone number is required")
	/* @Pattern(regexp = "(\\d{10})", message = "Invalid phone number") */
	private String phoneNumber;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email address")
	private String email;

	@NotNull(message = "Survey date is required")
	private LocalDate dateOfSurvey;

	@NotNull(message = "Please select what you liked most about the campus")
	@Enumerated(EnumType.STRING)
	private List<LikedMostOption> likedMost;

	@NotNull(message = "Please select how you became interested in the university")
	@Enumerated(EnumType.STRING)
	private InterestSource interestSource;

	@NotNull(message = "Please select the likelihood of recommending the school")
	@Enumerated(EnumType.STRING)
	private RecommendLikelihood recommendLikelihood;
}
