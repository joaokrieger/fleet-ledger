package br.com.jek.controller;

import br.com.jek.data.dto.delivery.DeliveryRequestDTO;
import br.com.jek.data.dto.delivery.DeliveryResponseDTO;
import br.com.jek.service.DeliveryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery")
@Tag(name = "Delivery", description = "Management of deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeliveryResponseDTO> findAll(){
        return deliveryService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DeliveryResponseDTO findById(@PathVariable("id") Long id){
        return deliveryService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeliveryResponseDTO create(@RequestBody @Valid DeliveryRequestDTO deliveryRequestDTO){
        return deliveryService.create(deliveryRequestDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeliveryResponseDTO update(@RequestBody @Valid DeliveryRequestDTO deliveryRequestDTO){
        return deliveryService.update(deliveryRequestDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        deliveryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/mark-as-delivered")
    public DeliveryResponseDTO markAsDelivered(@PathVariable Long id) {
        return deliveryService.markAsDelivered(id);
    }
}
