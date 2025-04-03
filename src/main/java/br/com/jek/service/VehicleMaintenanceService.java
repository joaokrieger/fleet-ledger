package br.com.jek.service;

import br.com.jek.controller.VehicleMaintenanceController;
import br.com.jek.data.dto.vehicleMaintenance.VehicleMaintenanceRequestDTO;
import br.com.jek.data.dto.vehicleMaintenance.VehicleMaintenanceResponseDTO;
import br.com.jek.exception.ResourceNotFoundException;
import br.com.jek.mapper.VehicleMaintenanceMapper;
import br.com.jek.model.Vehicle;
import br.com.jek.model.VehicleMaintenance;
import br.com.jek.repository.VehicleMaintenanceRepository;
import br.com.jek.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class VehicleMaintenanceService {

    @Autowired
    private VehicleMaintenanceRepository vehicleMaintenanceRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleMaintenanceResponseDTO> findAll() {
        List<VehicleMaintenanceResponseDTO> vehicleMaintenances = vehicleMaintenanceRepository.findAll()
                .stream()
                .map(VehicleMaintenanceMapper::toDTO)
                .collect(Collectors.toList());

        vehicleMaintenances.forEach(this::addHateoasLinks);
        return vehicleMaintenances;
    }

    public VehicleMaintenanceResponseDTO findById(Long id) {
        var entity = vehicleMaintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        VehicleMaintenanceResponseDTO vehicleMaintenanceResponseDTO = VehicleMaintenanceMapper.toDTO(entity);
        addHateoasLinks(vehicleMaintenanceResponseDTO);
        return vehicleMaintenanceResponseDTO;
    }

    public VehicleMaintenanceResponseDTO create(VehicleMaintenanceRequestDTO vehicleMaintenanceRequestDTO) {
        Vehicle vehicle = vehicleRepository.findById(vehicleMaintenanceRequestDTO.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found!"));

        VehicleMaintenance vehicleMaintenance = VehicleMaintenanceMapper.toEntity(vehicleMaintenanceRequestDTO, vehicle);

        VehicleMaintenanceResponseDTO vehicleMaintenanceResponseDTO = VehicleMaintenanceMapper.toDTO(vehicleMaintenanceRepository.save(vehicleMaintenance));
        addHateoasLinks(vehicleMaintenanceResponseDTO);
        return vehicleMaintenanceResponseDTO;
    }

    public VehicleMaintenanceResponseDTO update(VehicleMaintenanceRequestDTO vehicleMaintenanceRequestDTO) {
        VehicleMaintenance vehicleMaintenance = vehicleMaintenanceRepository.findById(vehicleMaintenanceRequestDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        Vehicle vehicle = vehicleRepository.findById(vehicleMaintenanceRequestDTO.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found!"));

        vehicleMaintenance.setVehicle(vehicle);
        vehicleMaintenance.setMaintenanceDate(vehicleMaintenanceRequestDTO.getMaintenanceDate());
        vehicleMaintenance.setDescription(vehicleMaintenanceRequestDTO.getDescription());
        vehicleMaintenance.setCost(vehicleMaintenanceRequestDTO.getCost());

        VehicleMaintenanceResponseDTO vehicleMaintenanceResponseDTO = VehicleMaintenanceMapper.toDTO(vehicleMaintenanceRepository.save(vehicleMaintenance));
        addHateoasLinks(vehicleMaintenanceResponseDTO);
        return vehicleMaintenanceResponseDTO;
    }

    public void delete(Long id) {
        VehicleMaintenance vehicleMaintenance = vehicleMaintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        vehicleMaintenanceRepository.delete(vehicleMaintenance);
    }

    private void addHateoasLinks(VehicleMaintenanceResponseDTO vehicleMaintenanceResponseDTO){
        vehicleMaintenanceResponseDTO.add(linkTo(methodOn(VehicleMaintenanceController.class).getVehicleMaintenanceById(vehicleMaintenanceResponseDTO.getId())).withSelfRel().withType("GET"));
        vehicleMaintenanceResponseDTO.add(linkTo(methodOn(VehicleMaintenanceController.class).getAllVehiclesMaintenances()).withRel("getAllVehiclesMaintenances").withType("GET"));
        vehicleMaintenanceResponseDTO.add(linkTo(methodOn(VehicleMaintenanceController.class).createVehicleMaintenance(new VehicleMaintenanceRequestDTO())).withRel("createVehicleMaintenance").withType("POST"));
        vehicleMaintenanceResponseDTO.add(linkTo(methodOn(VehicleMaintenanceController.class).updateVehicleMaintenance(new VehicleMaintenanceRequestDTO())).withRel("updateVehicleMaintenance").withType("PUT"));
        vehicleMaintenanceResponseDTO.add(linkTo(methodOn(VehicleMaintenanceController.class).deleteVehicleMaintenance(vehicleMaintenanceResponseDTO.getId())).withRel("deleteVehicleMaintenance").withType("DELETE"));
    }
}
