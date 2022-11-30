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
class DrivingLicenceGenerationServiceTest {

    @InjectMocks
    private DrivingLicenceGeneratorService service;

    private DrivingLicence serviceLicense;
    final String SocialNumber="12345";
    @Mock
    DrivingLicence License=DrivingLicenceGeneratorService.CreatedrivingLicense(SocialNumber);


    @Mock
    private InMemoryDatabase database;

    @Test
    void should_have_12_point(){
        Assertions.assertEquals(12,License.getAvailablePoints());
    }
    @Test
    void should_social_number_not_null(){
        Assertions.assertNotNull(License.getDriverSocialSecurityNumber());
    }
    @Test
    void should_social_number_is_one_in_parameters(){
        Assertions.assertEquals(SocialNumber,License.getDriverSocialSecurityNumber());
    }
    @Test
    void should_be_in_database(){
        final var id =License.getId();
        Assertions.assertNotEquals(Optional.empty(),database.findById(id));
    }


}