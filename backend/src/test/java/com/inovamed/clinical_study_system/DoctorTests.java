package com.inovamed.clinical_study_system;


import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.doctor.DoctorExperienceEnum;
import com.inovamed.clinical_study_system.model.doctor.DoctorUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorTests {

    private Doctor doctor;
    private static final String DEFAULT_NAME = "Dr. John Doe";
    private static final String DEFAULT_EMAIL = "john.doe@example.com";
    private static final String DEFAULT_CLINIC = "Clinic A";
    private static final String DEFAULT_SPECIALTY = "Cardiology";
    private static final DoctorExperienceEnum DEFAULT_EXPERIENCE = DoctorExperienceEnum.EXPERIENCE;
    private static final String DEFAULT_CRM = "123456";
    private static final String DEFAULT_PHONE = "1234567890";
    private static final String DEFAULT_PASSWORD = "password";

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setName(DEFAULT_NAME);
        doctor.setEmail(DEFAULT_EMAIL);
        doctor.setClinic(DEFAULT_CLINIC);
        doctor.setSpecialty(DEFAULT_SPECIALTY);
        doctor.setDoctorExperienceEnum(DEFAULT_EXPERIENCE);
        doctor.setCrm(DEFAULT_CRM);
        doctor.setPhone(DEFAULT_PHONE);
        doctor.setPassword(DEFAULT_PASSWORD);
    }

    @Test
    void testUpdateWithValidData() {
        DoctorUpdateDTO updateDTO = new DoctorUpdateDTO(
                "Dr. Jane Doe",
                "jane.doe@example.com",
                "Clinic B",
                "Neurology",
                DoctorExperienceEnum.EXPERIENCE,
                "654321",
                "0987654321",
                "newpassword"
        );

        doctor.update(updateDTO);

        assertEquals("Dr. Jane Doe", doctor.getName());
        assertEquals("jane.doe@example.com", doctor.getEmail());
        assertEquals("Clinic B", doctor.getClinic());
        assertEquals("Neurology", doctor.getSpecialty());
        assertEquals(DoctorExperienceEnum.EXPERIENCE, doctor.getDoctorExperienceEnum());
        assertEquals("654321", doctor.getCrm());
        assertEquals("0987654321", doctor.getPhone());
        assertEquals("newpassword", doctor.getPassword());
    }

    @Test
    void testUpdateWithPartialData() {
        DoctorUpdateDTO partialUpdateDTO = new DoctorUpdateDTO(
                null,
                "new.email@example.com",
                null,
                null,
                null,
                null,
                null,
                null
        );

        doctor.update(partialUpdateDTO);

        assertEquals(DEFAULT_NAME, doctor.getName()); // Não deve mudar
        assertEquals("new.email@example.com", doctor.getEmail()); // Deve atualizar
        assertEquals(DEFAULT_CLINIC, doctor.getClinic()); // Não deve mudar
        assertEquals(DEFAULT_SPECIALTY, doctor.getSpecialty()); // Não deve mudar
        assertEquals(DEFAULT_EXPERIENCE, doctor.getDoctorExperienceEnum()); // Não deve mudar
        assertEquals(DEFAULT_CRM, doctor.getCrm()); // Não deve mudar
        assertEquals(DEFAULT_PHONE, doctor.getPhone()); // Não deve mudar
        assertEquals(DEFAULT_PASSWORD, doctor.getPassword()); // Não deve mudar
    }

    @Test
    void testUpdateWithAllNullValues() {
        DoctorUpdateDTO allNullDTO = new DoctorUpdateDTO(null, null, null, null, null, null, null, null);

        doctor.update(allNullDTO);

        assertEquals(DEFAULT_NAME, doctor.getName());
        assertEquals(DEFAULT_EMAIL, doctor.getEmail());
        assertEquals(DEFAULT_CLINIC, doctor.getClinic());
        assertEquals(DEFAULT_SPECIALTY, doctor.getSpecialty());
        assertEquals(DEFAULT_EXPERIENCE, doctor.getDoctorExperienceEnum());
        assertEquals(DEFAULT_CRM, doctor.getCrm());
        assertEquals(DEFAULT_PHONE, doctor.getPhone());
        assertEquals(DEFAULT_PASSWORD, doctor.getPassword());
    }
}
