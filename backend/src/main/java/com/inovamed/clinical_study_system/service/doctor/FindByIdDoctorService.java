package com.inovamed.clinical_study_system.service.doctor;

import com.inovamed.clinical_study_system.model.doctor.DoctorResponseDTO;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindByIdDoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorResponseDTO execute(Long id){

        return doctorRepository.findById(id).orElseThrow(()->{
            return new RuntimeException("Doctor not found");
        }).toResponseDTO();
    }

}
