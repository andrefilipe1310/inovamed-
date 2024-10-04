package com.inovamed.clinical_study_system.service.doctor;

import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.doctor.DoctorRequestDTO;
import com.inovamed.clinical_study_system.model.doctor.DoctorResponseDTO;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateDoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorResponseDTO execute(Long id, DoctorRequestDTO doctorRequestDTO) {
        Doctor updateDoctor = doctorRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Doctor not found");
        });
        updateDoctor.update(doctorRequestDTO);
        return doctorRepository.save(updateDoctor).toResponseDTO();
    }

}
