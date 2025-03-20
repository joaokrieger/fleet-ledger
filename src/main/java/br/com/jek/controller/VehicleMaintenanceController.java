package br.com.jek.controller;

import br.com.jek.data.dto.vehicleMaintenance.VehicleMaintenanceRequestDTO;
import br.com.jek.data.dto.vehicleMaintenance.VehicleMaintenanceResponseDTO;
import br.com.jek.service.VehicleMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle-maintenance")
public class VehicleMaintenanceController {

    @Autowired
    private VehicleMaintenanceService vehicleMaintenanceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleMaintenanceResponseDTO> findAll(){
        return vehicleMaintenanceService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleMaintenanceResponseDTO findById(@PathVariable("id") Long id){
        return vehicleMaintenanceService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleMaintenanceResponseDTO create(@RequestBody VehicleMaintenanceRequestDTO vehicleMaintenanceRequestDTO){
        return vehicleMaintenanceService.create(vehicleMaintenanceRequestDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleMaintenanceResponseDTO update(@RequestBody VehicleMaintenanceRequestDTO vehicleMaintenanceRequestDTO){
        return vehicleMaintenanceService.update(vehicleMaintenanceRequestDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        vehicleMaintenanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
