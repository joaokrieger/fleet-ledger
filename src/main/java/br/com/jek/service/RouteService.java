package br.com.jek.service;

import br.com.jek.controller.RouteController;
import br.com.jek.data.dto.RouteDTO;
import br.com.jek.exception.ResourceNotFoundException;
import br.com.jek.model.Route;
import br.com.jek.repository.RouteRepository;
import static br.com.jek.mapper.ObjectMapper.parseObject;
import static br.com.jek.mapper.ObjectMapper.parseListObjects;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<RouteDTO> findAll() {
        List<RouteDTO> routes = parseListObjects(routeRepository.findAll(), RouteDTO.class);
        routes.forEach(this::addHateoasLinks);
        return routes;
    }

    public RouteDTO findById(Long id) {
        var entity = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        RouteDTO routeDTO = parseObject(entity, RouteDTO.class);
        addHateoasLinks(routeDTO);
        return routeDTO;
    }

    public RouteDTO create(RouteDTO route) {
        var entity = parseObject(route, Route.class);
        RouteDTO routeDTO = parseObject(routeRepository.save(entity), RouteDTO.class);
        addHateoasLinks(routeDTO);
        return routeDTO;
    }

    public RouteDTO update(RouteDTO route) {
        var entity = routeRepository.findById(route.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setOrigin(route.getOrigin());
        entity.setDestination(route.getDestination());

        RouteDTO routeDTO = parseObject(routeRepository.save(entity), RouteDTO.class);
        addHateoasLinks(routeDTO);
        return routeDTO;
    }

    public void delete(Long id) {
        var entity = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        routeRepository.delete(entity);
    }

    private void addHateoasLinks(RouteDTO routeDTO){
        routeDTO.add(linkTo(methodOn(RouteController.class).getRouteById(routeDTO.getId())).withSelfRel().withType("GET"));
        routeDTO.add(linkTo(methodOn(RouteController.class).getAllRoutes()).withRel("getAllRoutes").withType("GET"));
        routeDTO.add(linkTo(methodOn(RouteController.class).getActiveRoutes()).withRel("getActiveRoutes").withType("GET"));
        routeDTO.add(linkTo(methodOn(RouteController.class).createRoute(routeDTO)).withRel("createRoute").withType("POST"));
        routeDTO.add(linkTo(methodOn(RouteController.class).updateRoute(routeDTO)).withRel("updateRoute").withType("PUT"));
        routeDTO.add(linkTo(methodOn(RouteController.class).deleteRoute(routeDTO.getId())).withRel("deleteRoute").withType("DELETE"));
    }

    public List<RouteDTO> findActiveRoutes() {
        List<RouteDTO> routes = parseListObjects(routeRepository.findActiveRoutes(), RouteDTO.class);
        routes.forEach(this::addHateoasLinks);
        return routes;
    }
}
