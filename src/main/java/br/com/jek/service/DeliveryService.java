package br.com.jek.service;

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
        List<Delivery> deliveries = deliveryRepository.findAll();
        return deliveries.stream()
                .map(DeliveryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DeliveryResponseDTO findById(Long id) {
        var entity = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return DeliveryMapper.toDTO(entity);
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

        return DeliveryMapper.toDTO(deliveryRepository.save(delivery));
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

        return DeliveryMapper.toDTO(deliveryRepository.save(delivery));
    }

    public void delete(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        deliveryRepository.delete(delivery);
    }

    public DeliveryResponseDTO markAsDelivered(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        delivery.setArrivalTime(LocalDateTime.now());
        return DeliveryMapper.toDTO(deliveryRepository.save(delivery));
    }
}