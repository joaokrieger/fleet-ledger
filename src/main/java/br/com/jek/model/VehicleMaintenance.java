package br.com.jek.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "vehicle_maintenance ")
public class VehicleMaintenance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date maintenanceDate ;

    @Column(nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VehicleMaintenance that = (VehicleMaintenance) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getVehicle(), that.getVehicle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getMaintenanceDate(), that.getMaintenanceDate()) && Objects.equals(getCost(), that.getCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVehicle(), getDescription(), getMaintenanceDate(), getCost());
    }
}
