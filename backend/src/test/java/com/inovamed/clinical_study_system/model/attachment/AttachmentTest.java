package com.inovamed.clinical_study_system.model.attachment;

import com.inovamed.clinical_study_system.model.clinical_study_representative.ClinicalStudyRepresentative;
import com.inovamed.clinical_study_system.model.notification.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AttachmentTest {
    @InjectMocks
    Attachment attachment;


    private static final long ID = 1L;
    private static final long USER_ID = 1L;
    private static final String NAME = "John";
 

    private static final MultipartFile FILE = new MultipartFile() {
        @SuppressWarnings("null")
        @Override
        public String getName() {
            return "";
        }

        @Override
        public String getOriginalFilename() {
            return "";
        }

        @Override
        public String getContentType() {
            return "";
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public long getSize() {
            return 0;
        }

        @SuppressWarnings("null")
        @Override
        public byte[] getBytes() throws IOException {
            return new byte[0];
        }

        @SuppressWarnings("null")
        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public void transferTo(@SuppressWarnings("null") File dest) throws IOException, IllegalStateException {

        }
        // Methods omitted for brevity
    };
    private static final MultipartFile NEW_FILE = new MultipartFile() {
        @SuppressWarnings("null")
        @Override
        public String getName() {
            return "";
        }

        @Override
        public String getOriginalFilename() {
            return "";
        }

        @Override
        public String getContentType() {
            return "";
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public long getSize() {
            return 0;
        }

        @SuppressWarnings("null")
        @Override
        public byte[] getBytes() throws IOException {
            return new byte[2];
        }

        @SuppressWarnings("null")
        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public void transferTo(@SuppressWarnings("null") File dest) throws IOException, IllegalStateException {

        }
        // Methods omitted for brevity
    };
    private static final String NEW_NAME = "John 2";


    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        setupTestEntities();
    }

    @Test
    void testInitialValues() throws IOException {

        assertNull(attachment.getNotification());
        assertEquals(1L, attachment.getId());
        assertEquals("John", attachment.getName());
        assertArrayEquals(FILE.getBytes(),attachment.getArchive());
        assertNull(attachment.getNotification());
        assertNotNull(attachment.getUser());

    }

    @Test
    void testUpdateMethod() throws IOException {
        AttachmentRequestDTO attachmentUpdateDTO = new AttachmentRequestDTO(NEW_NAME, NEW_FILE.getBytes(), USER_ID);

        attachment.update(attachmentUpdateDTO);

        assertEquals("John 2",attachment.getName());
        assertArrayEquals(NEW_FILE.getBytes(),attachment.getArchive());
        assertEquals(1L,USER_ID);

    }

    @Test
    void testNotificationsAssociations() {

        Notification notifications = new Notification();


        attachment.setNotification(notifications);

        assertNotNull(attachment);
    }




    private void setupTestEntities() throws IOException {
        attachment.setName(NAME);
        attachment.setUser(new ClinicalStudyRepresentative(ID, NAME, "123456", "Role", "Experience", List.of(), List.of()));
        attachment.setArchive(FILE.getBytes());
        attachment.setId(ID);

    }

}