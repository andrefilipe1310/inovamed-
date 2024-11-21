package com.inovamed.clinical_study_system.Doctor;

import com.inovamed.clinical_study_system.controller.DoctorController;
import com.inovamed.clinical_study_system.model.doctor.DoctorRequestDTO;
import com.inovamed.clinical_study_system.model.doctor.DoctorResponseDTO;
import com.inovamed.clinical_study_system.model.doctor.DoctorUpdateDTO;
import com.inovamed.clinical_study_system.service.doctor.*;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoctorControllerTests {

    @Mock
    private CreateDoctorService createDoctorService;
    @Mock
    private FindAllDoctorService findAllDoctorService;
    @Mock
    private FindByIdDoctorService findByIdDoctorService;
    @Mock
    private UpdateDoctorService updateDoctorService;
    @Mock
    private DeleteDoctorService deleteDoctorService;

    @InjectMocks
    private DoctorController doctorController;

    private DoctorRequestDTO requestDTOMock;
    private DoctorResponseDTO responseDTOMock;
    private DoctorUpdateDTO updateDTOMock;
    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        requestDTOMock = new DoctorRequestDTO(
                "Dr. Test",
                "test@example.com",
                null,
                null,
                "Test Clinic",
                "1234567890",
                "123456",
                "Cardiology",
                "123456"
        );

        responseDTOMock = new DoctorResponseDTO(
                1L,
                "dasdasdasdasdasd",
                "Dr. Test",
                "test@example.com",
                "dasdasdasdasdasd",
                null,
                null,
                "Test Clinic",
                "1234567890",
                "Cardiology",
                "123456",
                List.of(),
                List.of(),
                List.of()
        );

        updateDTOMock = new DoctorUpdateDTO(
                "Dr. Updated",
                "updated@example.com",
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Nested
    @DisplayName("Create Doctor Tests")
    class CreateDoctorTests {

        @Test
        @DisplayName("Should create a new doctor")
        void shouldCreateDoctor() {
            when(createDoctorService.execute(any(DoctorRequestDTO.class))).thenReturn(responseDTOMock);

            ResponseEntity<DoctorResponseDTO> response = doctorController.create(requestDTOMock);

            assertNotNull(response);
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals(responseDTOMock, response.getBody());

            verify(createDoctorService).execute(requestDTOMock);
        }
    }



    @Nested
    @DisplayName("Find Doctor By Id Tests")
    class FindDoctorByIdTests {

        @Test
        @DisplayName("Should find a doctor by id")
        void shouldFindDoctorById() {
            when(findByIdDoctorService.execute(1L)).thenReturn(responseDTOMock);

            ResponseEntity<DoctorResponseDTO> response = doctorController.findById(1L);

            assertNotNull(response);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(responseDTOMock, response.getBody());

            verify(findByIdDoctorService).execute(1L);
        }
    }

    @Nested
    @DisplayName("Update Doctor Tests")
    class UpdateDoctorTests {

        @Test
        @DisplayName("Should update an existing doctor")
        void shouldUpdateDoctor() {
            when(updateDoctorService.execute(1L, updateDTOMock)).thenReturn(responseDTOMock);

            ResponseEntity<DoctorResponseDTO> response = doctorController.update(1L, updateDTOMock);

            assertNotNull(response);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(responseDTOMock, response.getBody());

            verify(updateDoctorService).execute(1L, updateDTOMock);
        }
    }

    @Nested
    @DisplayName("Delete Doctor Tests")
    class DeleteDoctorTests {

        @Test
        @DisplayName("Should delete a doctor")
        void shouldDeleteDoctor() {
            when(deleteDoctorService.execute(1L)).thenReturn("Doctor deleted successfully");

            ResponseEntity<String> response = doctorController.delete(1L);

            assertNotNull(response);
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            assertEquals("Doctor deleted successfully", response.getBody());

            verify(deleteDoctorService).execute(1L);
        }
    }

}
