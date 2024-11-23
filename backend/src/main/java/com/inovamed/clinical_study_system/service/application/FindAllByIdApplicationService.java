//package com.inovamed.clinical_study_system.service.application;
//
//import com.inovamed.clinical_study_system.model.application.ApplicationResponseDTO;
//import com.inovamed.clinical_study_system.repository.ApplicationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class FindAllByIdApplicationService {
//    @Autowired
//    ApplicationRepository applicationRepository;
//    @Autowired
//    ApplicationMapperDTOService applicationMapperDTOService;
//    @Autowired
//
//
//    public List<ApplicationResponseDTO> execute(int code){
//        return applicationRepository.findByResearchCode(code)
//                .stream().map(application -> {
//                    return applicationMapperDTOService.toDTO(application);
//                })
//                .collect(Collectors.toList());
//    }
//}
