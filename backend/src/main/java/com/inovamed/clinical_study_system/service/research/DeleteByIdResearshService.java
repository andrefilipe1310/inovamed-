package com.inovamed.clinical_study_system.service.research;


import com.inovamed.clinical_study_system.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteByIdResearshService {
    @Autowired
    private ResearchRepository researchRepository;

    public String execute(Long id){
        this.researchRepository.deleteById(id);

        if(this.researchRepository.existsById(id)){
            throw new RuntimeException("Falid deleted Research.");
        }
        return "Research "+id+" deleted.";
    }
}
