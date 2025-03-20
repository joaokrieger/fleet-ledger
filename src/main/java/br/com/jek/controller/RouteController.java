package br.com.jek.controller;

import br.com.jek.data.dto.RouteDTO;
import br.com.jek.service.RouteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/route")
@Tag(name = "Route", description = "Management of delivery routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RouteDTO> findAll(){
        return routeService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteDTO findById(@PathVariable("id") Long id){
        return routeService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteDTO create(@RequestBody @Valid RouteDTO routeDTO){
        return routeService.create(routeDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteDTO update(@RequestBody @Valid RouteDTO routeDTO){
        return routeService.update(routeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        routeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
