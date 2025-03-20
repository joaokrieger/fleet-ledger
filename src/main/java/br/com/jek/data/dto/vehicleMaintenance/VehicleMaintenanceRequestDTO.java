package br.com.jek.data.dto.vehicleMaintenance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VehicleMaintenanceRequestDTO implements Serializable {

    private Long id;

    @NotNull(message = "The vehicle ID is required")
    private Long vehicleId;

    @NotBlank(message = "The description cannot be blank")
    private String description;

    @NotNull(message = "The maintenance date is required")
    private Date maintenanceDate ;

    @NotNull(message = "The cost is required")
    private BigDecimal cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
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
