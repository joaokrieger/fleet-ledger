package br.com.jek.data.dto;

import br.com.jek.model.Route;

import java.io.Serializable;
import java.util.Objects;

public class RouteDTO implements Serializable {

    private Long id;
    private String origin;
    private String destination;

    public RouteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(getId(), route.getId()) && Objects.equals(getOrigin(), route.getOrigin()) && Objects.equals(getDestination(), route.getDestination());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrigin(), getDestination());
    }
}
