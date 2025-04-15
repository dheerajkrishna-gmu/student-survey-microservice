package com.assginment.swe645.studentSurveyMicroservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assginment.swe645.studentSurveyMicroservice.Entities.StudentEntity;
import com.assginment.swe645.studentSurveyMicroservice.Service.StudentService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/survey")
@CrossOrigin
public class StudentController {

	@Autowired
	private StudentService studentService;

	// GET all surveys
	@GetMapping("/all")
	public ResponseEntity<List<StudentEntity>> getAllSurveys() {
		List<StudentEntity> surveys = studentService.getAllSurveys();
		if (surveys.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content if no surveys found
		}
		return new ResponseEntity<>(surveys, HttpStatus.OK); // 200 OK with survey list
	}

	// GET a specific survey by ID
	@GetMapping("/{id}")
	public ResponseEntity<?> getSurveyById(@PathVariable Long id) {
		try {
			return new ResponseEntity<StudentEntity>(studentService.getSurveyById(id), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error for
																					// unexpected issues
		}

	}

	// POST a new survey with validation
	@PostMapping("/create")
	public ResponseEntity<StudentEntity> createSurvey(@Valid @RequestBody StudentEntity student) {
		try {
			StudentEntity savedSurvey = studentService.createSurvey(student);
			return new ResponseEntity<>(savedSurvey, HttpStatus.CREATED); // 201 Created
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error for
																					// unexpected issues
		}
	}

	// PUT (update) a survey by ID with validation
	@PatchMapping("/update/{id}")
	public ResponseEntity<?> updateSurvey(@PathVariable Long id, @Valid @RequestBody StudentEntity updatedSurvey) {
		try {
			StudentEntity updated = studentService.updateSurvey(id, updatedSurvey);

			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error for
			// unexpected issues
		}
		// successful update
	}

	// DELETE a survey by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSurvey(@PathVariable Long id) {

		try {
			studentService.deleteSurvey(id);
			return new ResponseEntity<>("survey successfully deleted!", HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error for
			// unexpected issues
		}

	}

}
