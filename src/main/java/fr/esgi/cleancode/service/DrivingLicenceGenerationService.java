package fr.esgi.cleancode.service;

import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceGenerationService {
    public DrivingLicence CreateDrivingLicense(String SocialNumber) {
        return DrivingLicence.builder().id(UUID.randomUUID()).driverSocialSecurityNumber(SocialNumber).build();
    }

    public boolean check_social_number(String social_number) {
        return social_number.length() == 15 && social_number.matches("[0-9]+");
    }
    
}
