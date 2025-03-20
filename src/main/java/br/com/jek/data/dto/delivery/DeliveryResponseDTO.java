package br.com.jek.data.dto.delivery;

import br.com.jek.model.Driver;
import br.com.jek.model.Route;
import br.com.jek.model.Vehicle;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DeliveryResponseDTO implements Serializable {

    private Long id;
    private Vehicle vehicle;
    private Driver driver;
    private Route route;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public DeliveryResponseDTO() {
    }

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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
