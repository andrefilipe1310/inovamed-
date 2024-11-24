package com.inovamed.clinical_study_system.service.research;


import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.exception.ResearchNotFoundException;
import com.inovamed.clinical_study_system.exception.UnauthorizedAccessException;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.research.FindPatientsAndDoctorIdsResponseDTO;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import com.inovamed.clinical_study_system.repository.PatientRepository;
import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindPatientsAndDoctorIdsByResearchCodeService {
    ResearchRepository researchRepository;
    ClinicalStudyRepresentiveRepository clinicalRepresentativeRepository;

    public FindPatientsAndDoctorIdsResponseDTO execute(int code, Long userId){
        List<Long> patientsId = new ArrayList<>();
        List<Long> doctorsId = new ArrayList<>();

        ClinicalStudyRepresentative clinicalRepresentative = clinicalRepresentativeRepository.findById(userId).orElseThrow(
                ()->{
                    return new ClinicalRepresentativeNotFoundException();
                }
        );


        Research research = researchRepository.findByCode(code).orElseThrow(
                ()->{
                    return new ResearchNotFoundException();
                }
        );
        if (!research.getClinicalRepresentative().getId().equals(clinicalRepresentative.getId())) {
            throw new UnauthorizedAccessException("Esta pesquisa não pertence ao representante clínico informado.");
        }

       research.getPatients().forEach(patient->{
           patientsId.add(patient.getId());
       });

        research.getDoctors().forEach(doctor->{
            patientsId.add(doctor.getId());
        });

        return new FindPatientsAndDoctorIdsResponseDTO(doctorsId,patientsId);


    }
}
