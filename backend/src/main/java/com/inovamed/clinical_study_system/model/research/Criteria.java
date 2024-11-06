package com.inovamed.clinical_study_system.model.research;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {
    private List<String> inclusion;
    private List<String> exclusion;
}
