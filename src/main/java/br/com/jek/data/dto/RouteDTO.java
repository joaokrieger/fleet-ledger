package br.com.jek.data.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class RouteDTO implements Serializable {

    private Long id;

    @NotBlank(message = "The route origin cannot be blank")
    private String origin;

    @NotBlank(message = "The route destination cannot be blank")
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
}
