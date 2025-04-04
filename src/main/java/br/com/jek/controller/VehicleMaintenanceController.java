package br.com.jek.controller;

import br.com.jek.data.dto.vehicleMaintenance.VehicleMaintenanceRequestDTO;
import br.com.jek.data.dto.vehicleMaintenance.VehicleMaintenanceResponseDTO;
import br.com.jek.service.VehicleMaintenanceService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle-maintenance")
@Tag(name = "Vehicle Maintenance", description = "Management of vehicle maintenance records")
public class VehicleMaintenanceController {

    private final VehicleMaintenanceService vehicleMaintenanceService;

    public VehicleMaintenanceController(VehicleMaintenanceService vehicleMaintenanceService) {
        this.vehicleMaintenanceService = vehicleMaintenanceService;
    }

    @GetMapping
    public ResponseEntity<List<VehicleMaintenanceResponseDTO>> getAllVehiclesMaintenances(){
        List<VehicleMaintenanceResponseDTO> vehiclesMaintenances = vehicleMaintenanceService.findAll();
        return ResponseEntity.ok(vehiclesMaintenances);
    }

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle Maintenance found successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicle Maintenance not found")
    })
    public ResponseEntity<VehicleMaintenanceResponseDTO> getVehicleMaintenanceById(@PathVariable("id") Long id){
        VehicleMaintenanceResponseDTO vehicleMaintenance = vehicleMaintenanceService.findById(id);
        return ResponseEntity.ok(vehicleMaintenance);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle Maintenance created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<VehicleMaintenanceResponseDTO> createVehicleMaintenance(@RequestBody @Valid VehicleMaintenanceRequestDTO vehicleMaintenanceRequestDTO){
        VehicleMaintenanceResponseDTO vehicleMaintenance = vehicleMaintenanceService.create(vehicleMaintenanceRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleMaintenance);
    }

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle Maintenance updated successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicle Maintenance not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<VehicleMaintenanceResponseDTO> updateVehicleMaintenance(@RequestBody @Valid VehicleMaintenanceRequestDTO vehicleMaintenanceRequestDTO){
        VehicleMaintenanceResponseDTO vehicleMaintenance = vehicleMaintenanceService.update(vehicleMaintenanceRequestDTO);
        return ResponseEntity.ok(vehicleMaintenance);
    }

    @DeleteMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vehicle Maintenance deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicle Maintenance not found")
    })
    public ResponseEntity<Void> deleteVehicleMaintenance(@PathVariable Long id){
        vehicleMaintenanceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vehicle/{licensePlate}")
    public ResponseEntity<List<VehicleMaintenanceResponseDTO>> getVehicleMaintenanceByLicensePlate(@PathVariable("licensePlate") String licensePlate){
        List<VehicleMaintenanceResponseDTO> vehiclesMaintenances = vehicleMaintenanceService.findByLicensePlate(licensePlate);
        return ResponseEntity.ok(vehiclesMaintenances);
    }
}
