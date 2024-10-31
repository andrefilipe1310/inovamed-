package com.inovamed.clinical_study_system;

import com.inovamed.clinical_study_system.model.doctor.Doctor;
import com.inovamed.clinical_study_system.model.doctor.DoctorExperienceEnum;
import com.inovamed.clinical_study_system.model.doctor.DoctorUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DoctorTests {

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
    }

    @Test
    @DisplayName("Deve criar um novo Doctor com valores padrão")
    void testCreateNewDoctor() {
        assertNotNull(doctor);
        assertNotNull(doctor.getKey(), "A chave UUID não deve ser nula");
        assertTrue(doctor.getKey().matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"));
    }

    @Test
    @DisplayName("Deve criar um Doctor com todos os atributos")
    void testCreateDoctorWithAllAttributes() {
        Doctor fullDoctor = new Doctor(
                1L,
                "uuid-key",
                "Dr. João Silva",
                "Clínica São Lucas",
                "Cardiologia",
                DoctorExperienceEnum.EXPERIENCE,
                "123456",
                "11999999999",
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        fullDoctor.setEmail("joao.silva@example.com");

        assertNotNull(fullDoctor);
        assertEquals(1L, fullDoctor.getId());
        assertEquals("Dr. João Silva", fullDoctor.getName());
        assertEquals("joao.silva@example.com", fullDoctor.getEmail());
        assertEquals("Clínica São Lucas", fullDoctor.getClinic());
        assertEquals("Cardiologia", fullDoctor.getSpecialty());
        assertEquals(DoctorExperienceEnum.EXPERIENCE, fullDoctor.getDoctorExperienceEnum());
        assertEquals("123456", fullDoctor.getCrm());
        assertEquals("11999999999", fullDoctor.getPhone());
        assertNotNull(fullDoctor.getApplicationsSubmitted());
        assertNotNull(fullDoctor.getNotifications());
        assertNotNull(fullDoctor.getPatients());
    }

    @Test
    @DisplayName("Deve atualizar apenas os campos não nulos do Doctor")
    void testPartialUpdate() {
        // Setup
        doctor.setName("Dr. Original");
        doctor.setEmail("original@example.com");
        doctor.setClinic("Clínica Original");
        doctor.setSpecialty("Especialidade Original");
        doctor.setCrm("000000");

        DoctorUpdateDTO updateDTO = new DoctorUpdateDTO(
                null,                           // nome não será alterado
                "novo@example.com",             // novo email
                null,                           // clínica não será alterada
                "Nova Especialidade",           // nova especialidade
                null,                           // experiência não será alterada
                null,                           // CRM não será alterado
                "11988888888",                  // novo telefone
                null                            // senha não será alterada
        );

        doctor.update(updateDTO);

        assertEquals("Dr. Original", doctor.getName(), "Nome não deveria mudar");
        assertEquals("novo@example.com", doctor.getEmail(), "Email deveria ser atualizado");
        assertEquals("Clínica Original", doctor.getClinic(), "Clínica não deveria mudar");
        assertEquals("Nova Especialidade", doctor.getSpecialty(), "Especialidade deveria ser atualizada");
        assertEquals("000000", doctor.getCrm(), "CRM não deveria mudar");
        assertEquals("11988888888", doctor.getPhone(), "Telefone deveria ser atualizado");
    }

    @Test
    @DisplayName("Deve atualizar todos os campos do Doctor")
    void testFullUpdate() {
        DoctorUpdateDTO updateDTO = new DoctorUpdateDTO(
                "Dr. Novo Nome",
                "novo@example.com",
                "Nova Clínica",
                "Nova Especialidade",
                DoctorExperienceEnum.NEVER,
                "654321",
                "11977777777",
                "novaSenha123"
        );

        doctor.update(updateDTO);

        assertEquals("Dr. Novo Nome", doctor.getName());
        assertEquals("novo@example.com", doctor.getEmail());
        assertEquals("Nova Clínica", doctor.getClinic());
        assertEquals("Nova Especialidade", doctor.getSpecialty());
        assertEquals(DoctorExperienceEnum.NEVER, doctor.getDoctorExperienceEnum());
        assertEquals("654321", doctor.getCrm());
        assertEquals("11977777777", doctor.getPhone());
        assertEquals("novaSenha123", doctor.getPassword());
    }

    @Test
    @DisplayName("Não deve modificar nenhum campo quando atualizar com DTO contendo apenas valores nulos")
    void testUpdateWithNullValues() {
        // Setup inicial
        doctor.setName("Dr. Original");
        doctor.setEmail("original@example.com");

        DoctorUpdateDTO nullDTO = new DoctorUpdateDTO(
                null, null, null, null, null, null, null, null
        );

        doctor.update(nullDTO);

        assertEquals("Dr. Original", doctor.getName());
        assertEquals("original@example.com", doctor.getEmail());
    }

    @Test
    @DisplayName("Deve manter a chave UUID original após atualizações")
    void testKeyPersistenceAfterUpdate() {
        String originalKey = doctor.getKey();

        DoctorUpdateDTO updateDTO = new DoctorUpdateDTO(
                "Novo Nome",
                "novo@example.com",
                null, null, null, null, null, null
        );

        doctor.update(updateDTO);

        assertEquals(originalKey, doctor.getKey(), "A chave UUID não deve mudar após atualizações");
    }
}