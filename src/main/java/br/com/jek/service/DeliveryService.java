package br.com.jek.service;

import br.com.jek.controller.DeliveryController;
import br.com.jek.data.dto.delivery.DeliveryRequestDTO;
import br.com.jek.data.dto.delivery.DeliveryResponseDTO;
import br.com.jek.exception.ResourceNotFoundException;
import br.com.jek.mapper.DeliveryMapper;
import br.com.jek.model.Delivery;
import br.com.jek.model.Driver;
import br.com.jek.model.Route;
import br.com.jek.model.Vehicle;
import br.com.jek.repository.DeliveryRepository;
import br.com.jek.repository.DriverRepository;
import br.com.jek.repository.RouteRepository;
import br.com.jek.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RouteRepository routeRepository;

    public List<DeliveryResponseDTO> findAll() {
        List<DeliveryResponseDTO> deliveries = deliveryRepository.findAll()
                .stream()
                .map(DeliveryMapper::toDTO)
                .collect(Collectors.toList());
        deliveries.forEach(this::addHateoasLinks);
        return deliveries;
    }

    public DeliveryResponseDTO findById(Long id) {
        var entity = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        DeliveryResponseDTO deliveryResponseDTO = DeliveryMapper.toDTO(entity);
        addHateoasLinks(deliveryResponseDTO);
        return deliveryResponseDTO;
    }

    public DeliveryResponseDTO create(DeliveryRequestDTO deliveryRequestDTO) {

        Vehicle vehicle = vehicleRepository.findById(deliveryRequestDTO.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found!"));

        Driver driver = driverRepository.findById(deliveryRequestDTO.getDriverId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found!"));

        Route route = routeRepository.findById(deliveryRequestDTO.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found!"));

        Delivery delivery = DeliveryMapper.toEntity(deliveryRequestDTO, vehicle, driver, route);
        delivery.setDepartureTime(LocalDateTime.now());

        DeliveryResponseDTO deliveryResponseDTO = DeliveryMapper.toDTO(deliveryRepository.save(delivery));
        addHateoasLinks(deliveryResponseDTO);
        return deliveryResponseDTO;
    }

    public DeliveryResponseDTO update(DeliveryRequestDTO deliveryRequestDTO) {

        Delivery delivery = deliveryRepository.findById(deliveryRequestDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        Vehicle vehicle = vehicleRepository.findById(deliveryRequestDTO.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found!"));

        Driver driver = driverRepository.findById(deliveryRequestDTO.getDriverId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found!"));

        Route route = routeRepository.findById(deliveryRequestDTO.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found!"));

        delivery.setVehicle(vehicle);
        delivery.setDriver(driver);
        delivery.setRoute(route);

        DeliveryResponseDTO deliveryResponseDTO = DeliveryMapper.toDTO(deliveryRepository.save(delivery));
        addHateoasLinks(deliveryResponseDTO);
        return deliveryResponseDTO;
    }

    public void delete(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        deliveryRepository.delete(delivery);
    }

    public void markAsDelivered(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        delivery.setArrivalTime(LocalDateTime.now());
        deliveryRepository.save(delivery);
    }

    private void addHateoasLinks(DeliveryResponseDTO deliveryResponseDTO){
        deliveryResponseDTO.add(linkTo(methodOn(DeliveryController.class).getDeliveryById(deliveryResponseDTO.getId())).withSelfRel().withType("GET"));
        deliveryResponseDTO.add(linkTo(methodOn(DeliveryController.class).getAllDeliveries()).withRel("getAllDeliveries").withType("GET"));
        deliveryResponseDTO.add(linkTo(methodOn(DeliveryController.class).createDelivery(new DeliveryRequestDTO())).withRel("createDelivery").withType("POST"));
        deliveryResponseDTO.add(linkTo(methodOn(DeliveryController.class).updateDelivery(new DeliveryRequestDTO())).withRel("updateDelivery").withType("PUT"));
        deliveryResponseDTO.add(linkTo(methodOn(DeliveryController.class).deleteDelivery(deliveryResponseDTO.getId())).withRel("deleteDelivery").withType("DELETE"));
        deliveryResponseDTO.add(linkTo(methodOn(DeliveryController.class).markDeliveryAsDelivered(deliveryResponseDTO.getId())).withRel("markDeliveryAsDelivered").withType("PUT"));
    }
}