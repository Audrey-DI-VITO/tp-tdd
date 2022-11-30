package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceFinderServiceTest {

    @InjectMocks
    private DrivingLicenceFinderService service;

    @Mock
    private InMemoryDatabase database;

    @Test
    void should_find() {
        final var uuid = UUID.randomUUID();
        final var drivingLicence = DrivingLicence.builder().id(uuid).build();

        when(database.findById(uuid)).thenReturn(Optional.of(drivingLicence));

        final var actual = service.findById(uuid);

        assertThat(actual).containsSame(drivingLicence);
        verify(database).findById(uuid);
        verifyNoMoreInteractions(database);
    }

    @Test
    void should_not_find() {
        final var uuid = UUID.randomUUID();

        when(database.findById(uuid)).thenReturn(Optional.empty());

        final var actual = service.findById(uuid);

        assertThat(actual).isEmpty();
        verify(database).findById(uuid);
        verifyNoMoreInteractions(database);
    }
}