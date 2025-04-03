package br.com.jek.service;

import br.com.jek.controller.VehicleController;
import br.com.jek.data.dto.VehicleDTO;
import static br.com.jek.mapper.ObjectMapper.parseObject;
import static br.com.jek.mapper.ObjectMapper.parseListObjects;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.jek.exception.ResourceNotFoundException;
import br.com.jek.model.Vehicle;
import br.com.jek.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleDTO> findAll() {
        List<VehicleDTO> vehicles = parseListObjects(vehicleRepository.findAll(), VehicleDTO.class);
        vehicles.forEach(this::addHateoasLinks);
        return vehicles;
    }

    public VehicleDTO findById(Long id) {
        var entity = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        VehicleDTO vehicleDTO = parseObject(entity, VehicleDTO.class);
        addHateoasLinks(vehicleDTO);
        return vehicleDTO;
    }

    public VehicleDTO create(VehicleDTO vehicle) {
        var entity = parseObject(vehicle, Vehicle.class);
        VehicleDTO vehicleDTO = parseObject(vehicleRepository.save(entity), VehicleDTO.class);
        addHateoasLinks(vehicleDTO);
        return vehicleDTO;
    }

    public VehicleDTO update(VehicleDTO vehicle) {
        var entity = vehicleRepository.findById(vehicle.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setBrand(vehicle.getBrand());
        entity.setLicensePlate(vehicle.getLicensePlate());
        entity.setModel(vehicle.getModel());
        entity.setYear(vehicle.getYear());

        VehicleDTO vehicleDTO = parseObject(vehicleRepository.save(entity), VehicleDTO.class);
        addHateoasLinks(vehicleDTO);
        return vehicleDTO;
    }

    public void delete(Long id) {
        var entity = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        vehicleRepository.delete(entity);
    }

    private void addHateoasLinks(VehicleDTO vehicleDTO){
        vehicleDTO.add(linkTo(methodOn(VehicleController.class).getVehicleById(vehicleDTO.getId())).withSelfRel().withType("GET"));
        vehicleDTO.add(linkTo(methodOn(VehicleController.class).getAllVehicles()).withRel("getAllVehicles").withType("GET"));
        vehicleDTO.add(linkTo(methodOn(VehicleController.class).createVehicle(vehicleDTO)).withRel("createVehicle").withType("POST"));
        vehicleDTO.add(linkTo(methodOn(VehicleController.class).updateVehicle(vehicleDTO)).withRel("updateVehicle").withType("PUT"));
        vehicleDTO.add(linkTo(methodOn(VehicleController.class).deleteVehicle(vehicleDTO.getId())).withRel("deleteVehicle").withType("DELETE"));
    }
}
