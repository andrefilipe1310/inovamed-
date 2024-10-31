package com.inovamed.clinical_study_system.service.patient;


import com.inovamed.clinical_study_system.exception.DoctorNotFoundException;
import com.inovamed.clinical_study_system.exception.PatientNotFoundException;
import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.patient.Patient;
import com.inovamed.clinical_study_system.model.patient.PatientRequestDTO;
import com.inovamed.clinical_study_system.model.patient.PatientResponseDTO;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreatePatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientDTOMapperService patientDTOMapperService;

    public PatientResponseDTO execute(PatientRequestDTO patientRequestDTO){
        Doctor doctor = doctorRepository.findByKey(patientRequestDTO.doctorKey())
                .orElseThrow(()->{
                    return new DoctorNotFoundException();
                });
       Patient patient = patientDTOMapperService.toEntity(patientRequestDTO,doctor);
        return patientDTOMapperService.toDTO(patientRepository.save(patient));

    };
}
