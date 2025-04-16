// DHEERAJ KRISHNA NAGULA
// G01448319 
// Akhilesh Dhavileswarapu
// G01472450
pipeline {
	agent any
   tools {
        maven 'Maven'
    }
	environment {
	DOCKERHUB_CREDENTIALS = credentials('docker-pass')
	BUILD_TIMESTAMP = "${new Date().format("yyyyMMdd-HHmmss")}"
	}
	
	stages {
		stage("Building the Student Survey Microservice Image") {
			steps{
				echo 'Building the application..'
				 dir('/var/lib/jenkins/workspace/microservice_pipeline/studentSurveyMicroservice'){
					sh 'rm -rf *.jar'
					sh 'mvn clean install -DskipTests'
					sh 'docker build -t dheerajkrishna141/student-survey-microservice:$BUILD_TIMESTAMP .'
				}
			}	
		}
		
		stage("Login to Docker Hub"){
			steps{
				script{
					sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'

				}
			}
		}
		stage("Pushing Image to DockerHub") {
			steps {
				script {
					sh 'docker push dheerajkrishna141/student-survey-microservice:$BUILD_TIMESTAMP'
					}
				}
			}
			
			
		stage("Deploying to Rancher") {
			steps {
				sh 'kubectl set image deployment/microservice container-1=dheerajkrishna141/student-survey-microservice:$BUILD_TIMESTAMP -n hw-1-namespace'
				sh 'kubectl rollout status deployment/microservice -n hw-1-namespace'
			}
		}
		
		
	}
	
}
