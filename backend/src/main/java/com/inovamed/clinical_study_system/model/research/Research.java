package com.inovamed.clinical_study_system.model.research;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_research")
@Getter
@Setter
@NoArgsConstructor
public class Research {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int patients;
    private String startDate;
    private String endDate;
    private String officialDocument;
}
