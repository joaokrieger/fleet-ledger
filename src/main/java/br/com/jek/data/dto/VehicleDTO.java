package br.com.jek.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

public class VehicleDTO extends RepresentationModel<VehicleDTO> implements Serializable {

    private Long id;

    @NotBlank(message = "The vehicle license plate cannot be blank")
    private String licensePlate;

    @NotBlank(message = "The vehicle model cannot be blank")
    private String model;

    @NotBlank(message = "The vehicle brand cannot be blank")
    private String brand;

    @NotNull(message = "The vehicle year is required")
    private int year;

    public VehicleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
