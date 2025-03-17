package br.com.jek.controller;

import br.com.jek.data.dto.VehicleDTO;
import br.com.jek.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> findAll(){
        return vehicleService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDTO findById(@PathVariable("id") Long id){
        return vehicleService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public VehicleDTO create(@RequestBody VehicleDTO vehicleDTO){
        return vehicleService.create(vehicleDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public VehicleDTO update(@RequestBody VehicleDTO vehicleDTO){
        return vehicleService.update(vehicleDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
