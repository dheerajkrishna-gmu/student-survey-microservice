package com.assginment.swe645.studentSurveyMicroservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assginment.swe645.studentSurveyMicroservice.Entities.StudentEntity;
import com.assginment.swe645.studentSurveyMicroservice.Repo.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<StudentEntity> getAllSurveys() {
		return studentRepository.findAll();
	}

	public StudentEntity getSurveyById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Survey with id: " + id + " not found!"));
	}
	
	public StudentEntity createSurvey(StudentEntity survey) {
		return studentRepository.save(survey);
	}
	

	public StudentEntity updateSurvey(Long id, StudentEntity updatedSurvey) {
		StudentEntity survey = getSurveyById(id);

		survey.setFirstName(updatedSurvey.getFirstName());
		survey.setLastName(updatedSurvey.getLastName());
		survey.setStreetAddress(updatedSurvey.getStreetAddress());
		survey.setCity(updatedSurvey.getCity());
		survey.setState(updatedSurvey.getState());
		survey.setZip(updatedSurvey.getZip());
		survey.setPhoneNumber(updatedSurvey.getPhoneNumber());
		survey.setEmail(updatedSurvey.getEmail());
		survey.setDateOfSurvey(updatedSurvey.getDateOfSurvey());
		survey.setLikedMost(updatedSurvey.getLikedMost());
		survey.setInterestSource(updatedSurvey.getInterestSource());
		survey.setRecommendLikelihood(updatedSurvey.getRecommendLikelihood());

		return studentRepository.save(survey);
	}

	public void deleteSurvey(Long id) {
		StudentEntity existingSurvey = getSurveyById(id);
		studentRepository.deleteById(id);
	}

	// Add more business logic or custom queries if needed
}