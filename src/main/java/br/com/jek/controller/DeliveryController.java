package br.com.jek.controller;

import br.com.jek.data.dto.delivery.DeliveryRequestDTO;
import br.com.jek.data.dto.delivery.DeliveryResponseDTO;
import br.com.jek.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery")
@Tag(name = "Delivery", description = "Management of deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryResponseDTO>> getAllDeliveries(){
        List<DeliveryResponseDTO> deliveries  = deliveryService.findAll();
        return ResponseEntity.ok(deliveries);
    }

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery found successfully"),
            @ApiResponse(responseCode = "404", description = "Delivery not found")
    })
    public ResponseEntity<DeliveryResponseDTO> getDeliveryById(@PathVariable("id") Long id){
        DeliveryResponseDTO delivery = deliveryService.findById(id);
        return ResponseEntity.ok(delivery);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delivery created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<DeliveryResponseDTO> createDelivery(@RequestBody @Valid DeliveryRequestDTO deliveryRequestDTO){
        DeliveryResponseDTO delivery = deliveryService.create(deliveryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(delivery);
    }

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery updated successfully"),
            @ApiResponse(responseCode = "404", description = "Delivery not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<DeliveryResponseDTO> updateDelivery(@RequestBody @Valid DeliveryRequestDTO deliveryRequestDTO){
        DeliveryResponseDTO delivery = deliveryService.update(deliveryRequestDTO);
        return ResponseEntity.ok(delivery);
    }

    @DeleteMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delivery deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Delivery not found")
    })
    public ResponseEntity<Void> deleteDelivery(@PathVariable("id") Long id){
        deliveryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/mark-as-delivered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delivery marked as delivered successfully"),
            @ApiResponse(responseCode = "404", description = "Delivery not found")
    })
    public ResponseEntity<Void> markDeliveryAsDelivered(@PathVariable Long id) {
        deliveryService.markAsDelivered(id);
        return ResponseEntity.noContent().build();
    }
}
