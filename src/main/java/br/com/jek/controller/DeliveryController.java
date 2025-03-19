package br.com.jek.controller;

import br.com.jek.data.dto.DeliveryDTO;
import br.com.jek.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeliveryDTO> findAll(){
        return deliveryService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DeliveryDTO findById(@PathVariable("id") Long id){
        return deliveryService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeliveryDTO create(@RequestBody @Valid DeliveryDTO deliveryDTO){
        return deliveryService.create(deliveryDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeliveryDTO update(@RequestBody @Valid DeliveryDTO deliveryDTO){
        return deliveryService.update(deliveryDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        deliveryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
