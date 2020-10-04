package core.utils;

import core.stored.*;

import java.util.Arrays;

public class Validator {
    private static boolean checkExistStatus(String toContains) {
        return Arrays.stream(Status.values()).anyMatch((status) -> status.name().equals(toContains));
    }

    private static boolean checkExistOrganizationType(String toContains) {
        return Arrays.stream(OrganizationType.values()).anyMatch((organizationType) -> organizationType.name().equals(toContains));
    }

    public static boolean validateWorker(Worker worker) {
        return worker.getId() != null &&
                (worker.getName() != null && !worker.getName().equals("")) &&
                (worker.getCoordinates().getX() > -548 && worker.getCoordinates().getX() != null) &&
                (worker.getCoordinates().getY() > -1024d && worker.getCoordinates().getY() != null) &&
                (worker.getSalary() > 0 && worker.getSalary() != null) &&
                checkExistStatus(worker.getStatus().toString()) &&
                validateOrganization(worker.getOrganization());
    }

    public static boolean validateOrganization(Organization organization) {
        return (organization.getFullName().length() < 743 && organization.getFullName() != null && !organization.getFullName().equals("")) &&
                checkExistOrganizationType(organization.getOrganizationType().toString()) &&
                validateAddress(organization.getOfficialAddress());
    }

    public static boolean validateAddress(Address officialAddress) {
        return officialAddress.getStreet() != null && !officialAddress.getStreet().equals("");
    }
}
