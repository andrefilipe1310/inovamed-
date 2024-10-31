package com.inovamed.clinical_study_system.model.clinical_study_representative;

import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.research.Research;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicalStudyRepresentativeTest {

    private ClinicalStudyRepresentative representative;

    @BeforeEach
    void setUp() {
        representative = new ClinicalStudyRepresentative();
        representative.setId(1L);
        representative.setName("Dr. Alice");
        representative.setPhone("123-456-7890");
        representative.setClinicalRole("Principal Investigator");
        representative.setExperiences("10 years in clinical research");
    }

    @Test
    void testInitialValues() {
        assertEquals(1L, representative.getId());
        assertEquals("Dr. Alice", representative.getName());
        assertEquals("123-456-7890", representative.getPhone());
        assertEquals("Principal Investigator", representative.getClinicalRole());
        assertEquals("10 years in clinical research", representative.getExperiences());
        assertNull(representative.getResearch());
        assertNull(representative.getNotifications());
    }

    @Test
    void testUpdateMethod() {
        ClinicalStudyRepresentativeUpdateDTO updateDTO = new ClinicalStudyRepresentativeUpdateDTO(
                "Dr. Alice Updated", "alice.updated@example.com", "newpassword123", "987-654-3210", "Senior Researcher", "15 years in clinical research"
        );

        representative.update(updateDTO);

        assertEquals("Dr. Alice Updated", representative.getName());
        assertEquals("alice.updated@example.com", representative.getEmail());
        assertEquals("newpassword123", representative.getPassword());
        assertEquals("987-654-3210", representative.getPhone());
        assertEquals("Senior Researcher", representative.getClinicalRole());
        assertEquals("15 years in clinical research", representative.getExperiences());
    }

    @Test
    void testResearchAndNotificationsAssociations() {
        List<Research> researchList = List.of(new Research(), new Research());
        List<Notification> notifications = List.of(new Notification(), new Notification());

        representative.setResearch(researchList);
        representative.setNotifications(notifications);

        assertEquals(2, representative.getResearch().size());
        assertEquals(2, representative.getNotifications().size());
    }
}
