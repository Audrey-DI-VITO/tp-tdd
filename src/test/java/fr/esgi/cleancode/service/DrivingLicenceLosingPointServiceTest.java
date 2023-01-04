package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceLosingPointServiceTest {

    @Mock
    private InMemoryDatabase database;

    @InjectMocks
    private DrivingLicenceLosingPointService serviceLosing = new DrivingLicenceLosingPointService(database);

    final String SocialNumber="12345";

    @Test
    void should_lose_five_points(){
        DrivingLicence licence = DrivingLicence.builder().id(UUID.randomUUID()).driverSocialSecurityNumber(SocialNumber).build();
        when(database.findById(licence.getId())).thenReturn(Optional.of(licence));
        licence = serviceLosing.losing_points(licence.getId(), 5);
        Assertions.assertEquals(7, licence.getAvailablePoints());
    }

    @Test
    void should_points_always_be_positive() {
        DrivingLicence licence = DrivingLicence.builder().id(UUID.randomUUID()).driverSocialSecurityNumber(SocialNumber).build();
        when(database.findById(licence.getId())).thenReturn(Optional.of(licence));
        licence = serviceLosing.losing_points(licence.getId(), 15);
        Assertions.assertEquals(0, licence.getAvailablePoints());
    }

    @Test
    void should_be_updated_in_database() {
        DrivingLicence licence = DrivingLicence.builder().id(UUID.randomUUID()).driverSocialSecurityNumber(SocialNumber).build();
        when(database.findById(licence.getId())).thenReturn(Optional.of(licence));
        licence = serviceLosing.losing_points(licence.getId(), 5);
        when(database.findById(licence.getId())).thenReturn(Optional.of(licence));

        final var actual = database.findById(licence.getId());
        var number = 0;

        if(actual.isPresent()) {
            number = actual.get().getAvailablePoints();
        }
        assertThat(number).isEqualTo(7);
    }
}