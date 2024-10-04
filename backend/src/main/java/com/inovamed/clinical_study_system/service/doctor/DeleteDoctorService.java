package com.inovamed.clinical_study_system.service.doctor;

import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteDoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public String execute(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return "Doctor Deleted";
        } else {
            return "Doctor not found.";
        }
    }


}
