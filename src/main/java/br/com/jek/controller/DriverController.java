package br.com.jek.controller;

import br.com.jek.data.dto.DriverDTO;
import br.com.jek.service.DriverService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
@Tag(name = "Driver", description = "Management of drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService){
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAllDrivers(){
        List<DriverDTO> drivers = driverService.findAll();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver found successfully"),
            @ApiResponse(responseCode = "404", description = "Driver not found")
    })
    public ResponseEntity<DriverDTO> getDriverById(@PathVariable("id") Long id){
        DriverDTO driver = driverService.findById(id);
        return ResponseEntity.ok(driver);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Driver created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<DriverDTO> createDriver(@RequestBody @Valid DriverDTO driverDTO){
        DriverDTO driver = driverService.create(driverDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(driver);
    }

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver updated successfully"),
            @ApiResponse(responseCode = "404", description = "Driver not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<DriverDTO> update(@RequestBody @Valid DriverDTO driverDTO){
        DriverDTO driver = driverService.update(driverDTO);
        return ResponseEntity.ok(driver);
    }

    @DeleteMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Driver deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Driver not found")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        driverService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
