package com.inovamed.clinical_study_system;

import com.inovamed.clinical_study_system.exception.DoctorNotFoundException;
import com.inovamed.clinical_study_system.model.doctor.*;
import com.inovamed.clinical_study_system.model.user.UserRoles;
import com.inovamed.clinical_study_system.repository.DoctorRepository;
import com.inovamed.clinical_study_system.service.doctor.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DoctorServicesTest {

    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private DoctorDTOMapperService doctorDTOMapperService;
    @InjectMocks
    private CreateDoctorService createDoctorService;
    @InjectMocks
    private UpdateDoctorService updateDoctorService;
    @InjectMocks
    private DeleteDoctorService deleteDoctorService;
    @InjectMocks
    private FindAllDoctorService findAllDoctorService;
    @InjectMocks
    private FindByIdDoctorService findByIdDoctorService;
    private Doctor doctorMock;
    private DoctorRequestDTO requestDTOMock;
    private DoctorResponseDTO responseDTOMock;

    @BeforeEach
    void setUp() {
        doctorMock = new Doctor();
        doctorMock.setId(1L);
        doctorMock.setName("Dr. Test");
        doctorMock.setEmail("test@example.com");
        doctorMock.setDoctorExperienceEnum(DoctorExperienceEnum.EXPERIENCE);
        doctorMock.setClinic("Test Clinic");
        doctorMock.setSpecialty("Cardiology");
        doctorMock.setCrm("123456");
        doctorMock.setPhone("1234567890");

        requestDTOMock = new DoctorRequestDTO(
                "Dr. Test",
                "test@example.com",
                UserRoles.DOCTOR,
                DoctorExperienceEnum.EXPERIENCE,
                "Test Clinic",
                "1234567890",
                "123456",
                "Cardiology",
                "123456"
        );

        responseDTOMock = new DoctorResponseDTO(
                1L,
                doctorMock.getKey(),
                "Dr. Test",
                "test@example.com",
                "dasdasdasdasdasd",
                UserRoles.DOCTOR,
                DoctorExperienceEnum.EXPERIENCE,
                "Test Clinic",
                "1234567890",
                "Cardiology",
                "123456",
                List.of(),
                List.of(),
                List.of()
        );
    }

    @Nested
    @DisplayName("Create Doctor Service Tests")
    class CreateDoctorServiceTests {

        @Test
        @DisplayName("Deve criar um novo médico com sucesso")
        void shouldCreateDoctor() {
            when(doctorDTOMapperService.toEntity(any(DoctorRequestDTO.class))).thenReturn(doctorMock);
            when(doctorRepository.save(any(Doctor.class))).thenReturn(doctorMock);
            when(doctorDTOMapperService.toDTO(any(Doctor.class))).thenReturn(responseDTOMock);

            DoctorResponseDTO result = createDoctorService.execute(requestDTOMock);

            assertNotNull(result);
            assertEquals(responseDTOMock.name(), result.name());
            assertEquals(responseDTOMock.email(), result.email());

            verify(doctorDTOMapperService).toEntity(requestDTOMock);
            verify(doctorRepository).save(doctorMock);
            verify(doctorDTOMapperService).toDTO(doctorMock);
        }
    }

    @Nested
    @DisplayName("Update Doctor Service Tests")
    class UpdateDoctorServiceTests {

        @Test
        @DisplayName("Deve atualizar um médico existente com sucesso")
        void shouldUpdateExistingDoctor() {
            DoctorUpdateDTO updateDTO = new DoctorUpdateDTO(
                    "Dr. Updated",
                    "updated@example.com",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctorMock));
            when(doctorRepository.save(any(Doctor.class))).thenReturn(doctorMock);
            when(doctorDTOMapperService.toDTO(any(Doctor.class))).thenReturn(responseDTOMock);

            DoctorResponseDTO result = updateDoctorService.execute(1L, updateDTO);

            assertNotNull(result);
            verify(doctorRepository).findById(1L);
            verify(doctorRepository).save(doctorMock);
        }

        @Test
        @DisplayName("Deve lançar exceção ao tentar atualizar médico inexistente")
        void shouldThrowExceptionWhenDoctorNotFound() {
            when(doctorRepository.findById(1L)).thenReturn(Optional.empty());

            DoctorUpdateDTO updateDTO = new DoctorUpdateDTO(
                    "Dr. Updated",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            assertThrows(DoctorNotFoundException.class, () ->
                    updateDoctorService.execute(1L, updateDTO)
            );
        }
    }

    @Nested
    @DisplayName("Delete Doctor Service Tests")
    class DeleteDoctorServiceTests {

        @Test
        @DisplayName("Deve deletar médico com sucesso")
        void shouldDeleteDoctor() {
            when(doctorRepository.existsById(1L)).thenReturn(false);

            String result = deleteDoctorService.execute(1L);

            assertNotNull(result);
            assertTrue(result.contains("success deleted"));
            verify(doctorRepository).deleteById(1L);
            verify(doctorRepository).existsById(1L);
        }

        @Test
        @DisplayName("Deve lançar exceção quando falhar ao deletar médico")
        void shouldThrowExceptionWhenDeleteFails() {
            when(doctorRepository.existsById(1L)).thenReturn(true);

            assertThrows(RuntimeException.class, () ->
                    deleteDoctorService.execute(1L)
            );
        }
    }

    @Nested
    @DisplayName("Find All Doctors Service Tests")
    class FindAllDoctorsServiceTests {

        @Test
        @DisplayName("Deve retornar lista de médicos")
        void shouldReturnListOfDoctors() {
            List<Doctor> doctors = Arrays.asList(doctorMock);
            when(doctorRepository.findAll()).thenReturn(doctors);
            when(doctorDTOMapperService.toDTO(any(Doctor.class))).thenReturn(responseDTOMock);

            List<DoctorResponseDTO> result = findAllDoctorService.execute();

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            verify(doctorRepository).findAll();
            verify(doctorDTOMapperService).toDTO(any(Doctor.class));
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando não houver médicos")
        void shouldReturnEmptyListWhenNoDoctors() {
            when(doctorRepository.findAll()).thenReturn(List.of());

            List<DoctorResponseDTO> result = findAllDoctorService.execute();

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(doctorRepository).findAll();
        }
    }

    @Nested
    @DisplayName("Find Doctor By Id Service Tests")
    class FindDoctorByIdServiceTests {

        @Test
        @DisplayName("Deve encontrar médico por ID com sucesso")
        void shouldFindDoctorById() {
            when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctorMock));
            when(doctorDTOMapperService.toDTO(any(Doctor.class))).thenReturn(responseDTOMock);

            DoctorResponseDTO result = findByIdDoctorService.execute(1L);

            assertNotNull(result);
            assertEquals(responseDTOMock.id(), result.id());
            verify(doctorRepository).findById(1L);
            verify(doctorDTOMapperService).toDTO(doctorMock);
        }

        @Test
        @DisplayName("Deve lançar exceção quando médico não for encontrado")
        void shouldThrowExceptionWhenDoctorNotFound() {
            when(doctorRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(DoctorNotFoundException.class, () ->
                    findByIdDoctorService.execute(1L)
            );

            verify(doctorRepository).findById(1L);
        }
    }
}