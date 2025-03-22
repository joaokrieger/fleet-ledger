package br.com.jek.controller;

import br.com.jek.data.dto.RouteDTO;
import br.com.jek.service.RouteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/route")
@Tag(name = "Route", description = "Management of delivery routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public ResponseEntity<List<RouteDTO>> getAllRoutes(){
        List<RouteDTO> routes = routeService.findAll();
        return ResponseEntity.ok(routes);
    }

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Route found successfully"),
            @ApiResponse(responseCode = "404", description = "Route not found")
    })
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable("id") Long id){
        RouteDTO route = routeService.findById(id);
        return ResponseEntity.ok(route);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Route created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<RouteDTO> createRoute(@RequestBody @Valid RouteDTO routeDTO){
        RouteDTO route = routeService.create(routeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(route);
    }

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Route updated successfully"),
            @ApiResponse(responseCode = "404", description = "Route not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<RouteDTO> updateRoute(@RequestBody @Valid RouteDTO routeDTO){
        RouteDTO route = routeService.update(routeDTO);
        return ResponseEntity.ok(route);
    }

    @DeleteMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Route deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Route not found")
    })
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id){
        routeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
