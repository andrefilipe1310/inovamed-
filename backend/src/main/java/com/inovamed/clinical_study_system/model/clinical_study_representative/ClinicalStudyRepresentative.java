package com.inovamed.clinical_study_system.model.clinical_study_representative;



import com.inovamed.clinical_study_system.model.notification.Notification;

import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "tb_clinical_study_representative")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalStudyRepresentative extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinical_study_representative_id")
    private Long id;
    private String name;
    private String phone;
    private String clinicalRole;
    private String experiences;
    //@OneToMany(mappedBy = "clinical_study_representative",cascade = CascadeType.ALL)
    @OneToMany
    List<Research> research;
    @OneToMany
    private List<Notification> notifications;





    public void update(ClinicalStudyRepresentativeUpdateDTO updateDTO) {
        this.name = updateDTO.name();
        this.setEmail(updateDTO.email());
        this.setPassword(updateDTO.password());
        this.phone = updateDTO.phone();
        this.clinicalRole = updateDTO.clinicalRole();
        this.experiences = updateDTO.experiences();

    }

}
