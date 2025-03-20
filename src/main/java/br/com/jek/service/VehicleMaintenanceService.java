package br.com.jek.service;

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

@Service
public class VehicleMaintenanceService {

    @Autowired
    private VehicleMaintenanceRepository vehicleMaintenanceRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleMaintenanceResponseDTO> findAll() {
        List<VehicleMaintenance> vehicleMaintenances = vehicleMaintenanceRepository.findAll();
        return vehicleMaintenances.stream()
                .map(VehicleMaintenanceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public VehicleMaintenanceResponseDTO findById(Long id) {
        var entity = vehicleMaintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return VehicleMaintenanceMapper.toDTO(entity);
    }

    public VehicleMaintenanceResponseDTO create(VehicleMaintenanceRequestDTO vehicleMaintenanceRequestDTO) {
        Vehicle vehicle = vehicleRepository.findById(vehicleMaintenanceRequestDTO.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found!"));

        VehicleMaintenance vehicleMaintenance = VehicleMaintenanceMapper.toEntity(vehicleMaintenanceRequestDTO, vehicle);

        return VehicleMaintenanceMapper.toDTO(vehicleMaintenanceRepository.save(vehicleMaintenance));
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

        return VehicleMaintenanceMapper.toDTO(vehicleMaintenanceRepository.save(vehicleMaintenance));
    }

    public void delete(Long id) {
        VehicleMaintenance vehicleMaintenance = vehicleMaintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        vehicleMaintenanceRepository.delete(vehicleMaintenance);
    }
}
