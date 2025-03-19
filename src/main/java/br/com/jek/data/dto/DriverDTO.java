package br.com.jek.data.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class DriverDTO implements Serializable {

    private Long id;

    @NotBlank(message = "The driver name cannot be blank")
    private String name;

    @NotBlank(message = "The driver license number cannot be blank")
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
}
