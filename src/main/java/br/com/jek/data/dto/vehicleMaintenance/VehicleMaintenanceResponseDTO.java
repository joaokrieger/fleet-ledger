package br.com.jek.data.dto.vehicleMaintenance;

import br.com.jek.model.Vehicle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VehicleMaintenanceResponseDTO implements Serializable {

    private Long id;
    private Vehicle vehicle;
    private String description;
    private Date maintenanceDate ;
    private BigDecimal cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
