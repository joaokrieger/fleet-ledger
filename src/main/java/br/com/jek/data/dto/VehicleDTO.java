package br.com.jek.data.dto;

import br.com.jek.model.Vehicle;

import java.io.Serializable;
import java.util.Objects;

public class VehicleDTO implements Serializable {

    private Long id;
    private String licensePlate;
    private String model;
    private String brand;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return getYear() == vehicle.getYear() && Objects.equals(getId(), vehicle.getId()) && Objects.equals(getLicensePlate(), vehicle.getLicensePlate()) && Objects.equals(getModel(), vehicle.getModel()) && Objects.equals(getBrand(), vehicle.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLicensePlate(), getModel(), getBrand(), getYear());
    }
}
