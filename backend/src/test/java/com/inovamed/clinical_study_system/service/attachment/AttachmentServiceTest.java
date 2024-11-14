package com.inovamed.clinical_study_system.service.attachment;

import com.inovamed.clinical_study_system.exception.AttachmentNotFoundException;
import com.inovamed.clinical_study_system.exception.ClinicalRepresentativeNotFoundException;
import com.inovamed.clinical_study_system.model.attachment.Attachment;
import com.inovamed.clinical_study_system.model.attachment.AttachmentCreateResponseDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentFindResponseDTO;
import com.inovamed.clinical_study_system.model.attachment.AttachmentRequestDTO;
import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.notification.Notification;
import com.inovamed.clinical_study_system.model.research.Research;
import com.inovamed.clinical_study_system.model.user.UserRoles;
import com.inovamed.clinical_study_system.repository.AttachmentRepository;
import com.inovamed.clinical_study_system.repository.ClinicalStudyRepresentiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AttachmentServiceTest {
   ;
    @InjectMocks
    private AttachmentService attachmentService;
    @Mock
    private AttachmentRepository attachmentRepository;
    @Mock
    private ClinicalStudyRepresentiveRepository clinicalRepository;

    private ClinicalStudyRepresentative clinicalRepresentative;
    private Attachment attachment;
    private AttachmentRequestDTO attachmentRequestDTO;
    private AttachmentRequestDTO attachmentUpdatetDTO;


    public static final long ID_ATTACHMENT = 1L;
    public static final String NAME_ATTACHMENT = "arquivo 1";
    public static final String MESSAGE = "file saved successfully";
    public static final long ID = 1L;
    public static final String NAME = "John";
    public static final String PHONE = "(81) 99999-9999";
    public static final String CLINICAL_ROLES = "EXPERT";
    public static final String EXPERIENCES = "neurologist";
    public static final List<Research> RESEARCH = List.of();
    public static final List<Notification> NOTIFICATIONS = List.of();
    public static final String EMAIL = "jonn@gmail.com";
    public static final String PASSWORD = "1234";
    public static final byte[] ARCHIVE = { 10, 20, 30, 40 };
    public static final UserRoles ROLES = UserRoles.DOCTOR;
    byte[] newArchive = {10,20,30};

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startClinicalRepresentative();

    }

    @Test
    void fileUploadAnReturnSuccessMessage() {
        when(clinicalRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(clinicalRepresentative));
        when(attachmentRepository.save(Mockito.any(Attachment.class))).thenReturn(attachment);

        AttachmentCreateResponseDTO response = attachmentService.upload(attachmentRequestDTO,ID);

        assertNotNull(response);

        assertEquals(NAME_ATTACHMENT,response.name());
        assertEquals(MESSAGE,response.message());
    }
    @Test
    void returnExceptionIfRepresentativeHasNotFoundInMethodUpload() {
        // Configura o comportamento do mock
        when(clinicalRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        // Verifica se a exceção é lançada ao chamar o método upload
        ClinicalRepresentativeNotFoundException exception = assertThrows(
                ClinicalRepresentativeNotFoundException.class,
                () -> attachmentService.upload(attachmentRequestDTO, ID)
        );

        // Verifica a mensagem da exceção
        assertEquals("Clinical Representative not found.", exception.getMessage());
    }

    @Test
    void WhenToCallTheMethodfindAllIsCalledReturnListOfAttachmentFindResponse() {
        when(attachmentRepository.findAll()).thenReturn(List.of(attachment));
        List<AttachmentFindResponseDTO> response = attachmentService.findAll();

        assertEquals(1,response.size());
        assertEquals(NAME_ATTACHMENT,response.get(0).name());
        assertNull(response.get(0).archive());


    }

    @Test
    void WhenToCallTheMethodfindByIdIsCalledReturnAttachmentFindResponseWithArchive() {
        when(attachmentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(attachment));

        AttachmentFindResponseDTO response = attachmentService.findById(ID);

        assertNotNull(response);
        assertEquals(NAME_ATTACHMENT,response.name());
        assertEquals(ARCHIVE,response.archive());


    }

    @Test
    void returnExceptionIfRepresentativeHasNotFoundInMethodUpdate() {
        // Configura o comportamento do mock
        when(attachmentRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());


        AttachmentNotFoundException exception = assertThrows(
                AttachmentNotFoundException.class,
                () -> attachmentService.update(ID_ATTACHMENT, attachmentUpdatetDTO)
        );

        // Verifica a mensagem da exceção
        assertEquals("Attachment not found.", exception.getMessage());
    }
    @Test
    void whenToCallTheUpdateReturnSuccess() {
        // Configura o comportamento do mock para retornar o anexo existente
        when(attachmentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(attachment));

        // Configura o comportamento do mock para retornar o anexo atualizado
        when(attachmentRepository.save(Mockito.any(Attachment.class))).thenReturn(new Attachment(
                ID_ATTACHMENT, "Novo nome", newArchive, null, clinicalRepresentative, null
        ));

        // Chama o método update
        AttachmentFindResponseDTO response = attachmentService.update(ID_ATTACHMENT, attachmentUpdatetDTO);

        // Verifica se o nome e o arquivo do anexo atualizado estão corretos
        assertEquals("Novo nome", response.name());
        assertArrayEquals(newArchive, response.archive());
    }

    @Test
    void deleteAttachmentShouldThrowExceptionIfNotFound() {
        // Configura o comportamento do mock para retornar vazio
        when(attachmentRepository.existsById(Mockito.anyLong())).thenReturn(false);

        // Verifica se a exceção é lançada
        AttachmentNotFoundException exception = assertThrows(
                AttachmentNotFoundException.class,
                () -> attachmentService.delete(ID_ATTACHMENT)
        );

        // Verifica a mensagem da exceção
        assertEquals("Attachment not found.", exception.getMessage());
    }

    @Test
    void deleteAttachmentShouldDeleteSuccessfully() {
        // Configura o comportamento do mock para indicar que o anexo existe
        when(attachmentRepository.existsById(Mockito.anyLong())).thenReturn(true);

        // Chama o método delete
        String response = attachmentService.delete(ID_ATTACHMENT);

        // Verifica se o método deleteById foi chamado no repositório
        verify(attachmentRepository, times(1)).deleteById(ID_ATTACHMENT);

        // Verifica a mensagem de sucesso
        assertEquals("attachment " + ID_ATTACHMENT + " deleted success.", response);
    }

    private void startClinicalRepresentative(){
        clinicalRepresentative = new ClinicalStudyRepresentative(ID, NAME, PHONE,CLINICAL_ROLES, EXPERIENCES, RESEARCH, NOTIFICATIONS);
        attachment = new Attachment(ID_ATTACHMENT, NAME_ATTACHMENT,ARCHIVE ,null,clinicalRepresentative,null);
        attachmentRequestDTO = new AttachmentRequestDTO(NAME,ARCHIVE,ID);
        attachmentUpdatetDTO = new AttachmentRequestDTO("Novo nome",newArchive,ID);
      
    }
}