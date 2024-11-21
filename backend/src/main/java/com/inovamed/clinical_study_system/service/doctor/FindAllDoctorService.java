package com.inovamed.clinical_study_system.service.doctor;

import com.inovamed.clinical_study_system.exception.DoctorNotFoundException;
import com.inovamed.clinical_study_system.model.doctor.DoctorResponseDTO;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllDoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorDTOMapperService doctorDTOMapperService;

    public DoctorResponseDTO execute(Long userId){
        return doctorDTOMapperService.toDTO(doctorRepository.findById(userId).orElseThrow(
                ()->{
                    return new DoctorNotFoundException();
                }
        ));
    }



}
