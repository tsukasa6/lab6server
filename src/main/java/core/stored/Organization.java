package core.stored;

import java.io.Serializable;

public class Organization implements Serializable {
    // Длина строки не должна быть больше 743, Строка не может быть пустой, Поле не может быть null
    private String fullName;
    // Поле может быть null
    private OrganizationType type;
    // Поле может быть null
    private Address officialAddress;

    public Organization(String fullName, OrganizationType type, Address officialAddress) {
        this.fullName = fullName;
        this.type = type;
        this.officialAddress = officialAddress;
    }

    public Organization() {}

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public OrganizationType getOrganizationType() {
        return this.type;
    }

    public void setOrganizationType(OrganizationType type) {
        this.type = type;
    }

    public Address getOfficialAddress() {
        return this.officialAddress;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }
}
