package br.com.jek.data.dto;

import br.com.jek.model.Driver;

import java.io.Serializable;
import java.util.Objects;

public class DriverDTO implements Serializable {

    private Long id;
    private String name;
    private String licenseNumber;

    public DriverDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(getId(), driver.getId()) && Objects.equals(getName(), driver.getName()) && Objects.equals(getLicenseNumber(), driver.getLicenseNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLicenseNumber());
    }
}
