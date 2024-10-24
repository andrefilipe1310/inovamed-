package com.inovamed.clinical_study_system.service.doctor;

import com.inovamed.clinical_study_system.exception.DoctorNotFoundException;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.doctor.DoctorResponseDTO;
import com.inovamed.clinical_study_system.model.doctor.DoctorUpdateDTO;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateDoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorDTOMapperService doctorDTOMapperService;

    public DoctorResponseDTO execute(Long id, DoctorUpdateDTO doctorUpdateDTO) {
        Doctor updateDoctor = doctorRepository.findById(id).orElseThrow(() -> {
            return new DoctorNotFoundException();
        });
        updateDoctor.update(doctorUpdateDTO);
        return doctorDTOMapperService.toDTO(doctorRepository.save(updateDoctor));
    }

}
