package com.inovamed.clinical_study_system.service.research;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.model.attachment.Attachment;
import com.inovamed.clinical_study_system.model.attachment.AttachmentFindResponseDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.research.*;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ResearchDTOMapperService {



    @Autowired
    private ClinicalStudyRepresentiveRepository clinicalRepresentiveRepository;
    @Autowired
    private ResearchRepository researchRepository;
    //show attachment volta os arquivos normais ou apenas um null
    public ResearchResponseDTO toDTO(Research research, boolean showAttachments){
        return new ResearchResponseDTO(
                research.getCode(),
                research.getTitle(),
                research.getArea(),
                research.getNumberOfPatients(),
                research.getAvailableVacancies(),
                research.getResponsibleDoctors(),
                research.getInstitutions(),
                research.getDescription(),
                research.getCriteria(),
                research.getStudyDuration(),
                research.getPhases(),
                research.getCurrentPhase(),
                research.getLocation(),
                showAttachments ? research.getAttachments().stream().map(attachment -> {
                    return new AttachmentFindResponseDTO(attachment.getName(),attachment.getArchive());
                }).collect(Collectors.toList()):null,
                research.getPatients().stream().map(patient -> {
                    return patient.getName()+ " "+" "+patient.getCode();
                }).collect(Collectors.toList()),
                research.getClinicalRepresentative().getName()+" "+research.getClinicalRepresentative().getId()
                );


    }

    public Research toEntity(ResearchRequestDTO researchRequestDTO, List<MultipartFile> files, Long userId,  List<Phases> phases) throws IOException {



        ClinicalStudyRepresentative clinicalRepresentative = clinicalRepresentiveRepository
                .findById(userId)
                .orElseThrow(()->{ return  new ClinicalRepresentativeNotFoundException();});
        Research research = new Research();

        //criando o anexo que vem na pesquisa


        List<Attachment> attachments = files.stream().map(file -> {
            Attachment attachment = new Attachment();

            attachment.setUser(clinicalRepresentative);
            attachment.setName("Research "+file.getName()+" "+clinicalRepresentative.getPublicKey());
            attachment.setResearch(research);
            try {

                attachment.setArchive(file.getBytes());
            } catch (IOException e) {

                throw new RuntimeException(e);
            }
            return  attachment;
        }).collect(Collectors.toList());



        research.setCode(generateUniqueCode());
        research.setTitle(researchRequestDTO.getTitle());
        research.setArea(researchRequestDTO.getArea());
        research.setNumberOfPatients(researchRequestDTO.getNumberOfPatients());
        research.setAvailableVacancies(researchRequestDTO.getAvailableVacancies());
        research.setResponsibleDoctors(researchRequestDTO.getResponsibleDoctors());
        research.setInstitutions(researchRequestDTO.getInstitutions());
        research.setDescription(researchRequestDTO.getDescription());
        research.setCriteria(researchRequestDTO.getCriteria());
        research.setStudyDuration(new StudyDuration(researchRequestDTO.getStart_date(),researchRequestDTO.getEnd_date()));
        research.setPhases(phases);
        research.setCurrentPhase(researchRequestDTO.getCurrentPhase().intValue());
        research.setLocation(researchRequestDTO.getLocation());
        research.setAttachments(attachments);
        research.setPatients(List.of());
        research.setClinicalRepresentative(clinicalRepresentative);

        return research;
    }

    private int generateUniqueCode() {
        Random random = new Random();
        int generatedCode;
        do {
            generatedCode = 1000 + random.nextInt(9000); // Gera um número de 1000 a 9999
        } while (researchRepository.findByCode(generatedCode).isPresent());
        return generatedCode;
    }
}
