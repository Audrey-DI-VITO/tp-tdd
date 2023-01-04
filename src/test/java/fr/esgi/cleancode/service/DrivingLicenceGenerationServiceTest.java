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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceGenerationServiceTest {

    @InjectMocks
    private DrivingLicenceGenerationService service = new DrivingLicenceGenerationService();

    @Mock
    private InMemoryDatabase database;

    @Test
    void should_not_be_null(){
        Assertions.assertFalse(service.check_social_number(""));
    }
    @Test
    void should_only_number(){
        Assertions.assertFalse(service.check_social_number("12345678911234b"));
    }
    @Test
    void should_15_length(){
        Assertions.assertFalse(service.check_social_number("123"));
    }

    @Test
    void should_social_number_not_null(){
        String SocialNumber="123456789123456";
        DrivingLicence License = service.CreateDrivingLicense(SocialNumber);
        Assertions.assertNotNull(License.getDriverSocialSecurityNumber());
    }

    @Test
    void should_have_12_point(){
        String SocialNumber="123456789123456";
        DrivingLicence License = service.CreateDrivingLicense(SocialNumber);
        Assertions.assertEquals(12,License.getAvailablePoints());
    }

    @Test
    void should_social_number_is_in_parameters(){
        String SocialNumber="123456789123456";
        DrivingLicence License = service.CreateDrivingLicense(SocialNumber);
        Assertions.assertEquals(SocialNumber, License.getDriverSocialSecurityNumber());
    }
    @Test
    void should_be_in_database(){
        String SocialNumber="123456789123456";
        DrivingLicence License = service.CreateDrivingLicense(SocialNumber);
        when(database.findById(License.getId())).thenReturn(Optional.of(License));
        Assertions.assertNotEquals(Optional.empty(),database.findById(License.getId()));
    }
}