package com.inovamed.clinical_study_system.service.doctor;

import com.inovamed.clinical_study_system.exception.DoctorDeletionFailedException;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteDoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
   

    public String execute(Long id){
        doctorRepository.deleteById(id);

        if(doctorRepository.existsById(id)){
            throw new DoctorDeletionFailedException(id);
        }

        return "Doctor "+id+" success deleted.";
    }
}
