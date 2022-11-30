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

    private DrivingLicence drivingLicence;

    @Mock
    private InMemoryDatabase database;

    @Test
    void should_find() {
        database.save(new UUID(4567, 4567), drivingLicence);
        Assertions.assertNotNull(service.findById(new UUID(4567, 4567)));
    }

    @Test
    void should_not_find() {
        Assertions.assertEquals(Optional.empty(), service.findById(new UUID(4567, 45677)));
    }
}