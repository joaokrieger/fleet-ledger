package br.com.jek.controller;

import br.com.jek.data.dto.DriverDTO;
import br.com.jek.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DriverDTO> findAll(){
        return driverService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DriverDTO findById(@PathVariable("id") Long id){
        return driverService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public DriverDTO create(@RequestBody DriverDTO driverDTO){
        return driverService.create(driverDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public DriverDTO update(@RequestBody DriverDTO driverDTO){
        return driverService.update(driverDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        driverService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
