package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceFinderServiceTest {

    @InjectMocks
    private DrivingLicenceFinderService service;

    @Mock
    private DrivingLicence drivingLicence;

    @Mock
    private DrivingLicenceIdGenerationService drivingLicenceIdGenerationService = new DrivingLicenceIdGenerationService();

    @Mock
    private InMemoryDatabase database;

    @Mock
    UUID uuid = drivingLicenceIdGenerationService.generateNewDrivingLicenceId();

    @Test
    void should_find() {
        database.save(uuid, drivingLicence);
        Assertions.assertNotNull(service.findById(uuid));
    }

    @Test
    void should_not_find() {
        Assertions.assertEquals(Optional.empty(), service.findById(drivingLicenceIdGenerationService.generateNewDrivingLicenceId()));
    }
}