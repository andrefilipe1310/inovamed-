package com.inovamed.clinical_study_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.inovamed.clinical_study_system.repository")
public class ClinicalStudySystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClinicalStudySystemApplication.class, args);
	}

}
