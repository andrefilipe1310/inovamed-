package com.inovamed.clinical_study_system.service.doctor;

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

    public List<DoctorResponseDTO> execute(){
        return doctorRepository.findAll().stream().map(doctor -> {
           return doctorDTOMapperService.toDTO(doctor);
        }).collect(Collectors.toList());
    }



}
