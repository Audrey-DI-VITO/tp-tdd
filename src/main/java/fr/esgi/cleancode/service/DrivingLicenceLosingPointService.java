package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;

import java.util.Optional;
import java.util.UUID;

public class DrivingLicenceLosingPointService {

    InMemoryDatabase database;

    public DrivingLicenceLosingPointService(InMemoryDatabase database) {
        this.database = database;
    }
    DrivingLicence losing_points(UUID id_licence, int points_to_lose) {
        DrivingLicenceFinderService finderService = new DrivingLicenceFinderService(database);
        Optional<DrivingLicence> drivingLicence = finderService.findById(id_licence);
        if(drivingLicence.isEmpty()) {
            throw new ResourceNotFoundException("Le permis de conduire recherché n'a pas été trouvé.");
        }

        drivingLicence = Optional.ofNullable(drivingLicence.get().withAvailablePoints(drivingLicence.get().getAvailablePoints() - points_to_lose));

        if(drivingLicence.get().getAvailablePoints() < 0) {
            drivingLicence = Optional.ofNullable(drivingLicence.get().withAvailablePoints(0));
        }

        return drivingLicence.get();
    }
}
