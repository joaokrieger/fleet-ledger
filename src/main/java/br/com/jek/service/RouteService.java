package br.com.jek.service;

import br.com.jek.data.dto.RouteDTO;
import br.com.jek.exception.ResourceNotFoundException;
import br.com.jek.model.Route;
import br.com.jek.repository.RouteRepository;
import static br.com.jek.mapper.ObjectMapper.parseObject;
import static br.com.jek.mapper.ObjectMapper.parseListObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<RouteDTO> findAll() {
        return parseListObjects(routeRepository.findAll(), RouteDTO.class);
    }

    public RouteDTO findById(Long id) {
        var entity = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return parseObject(entity, RouteDTO.class);
    }

    public RouteDTO create(RouteDTO routeDTO) {
        Route route = parseObject(routeDTO, Route.class);
        return parseObject(routeRepository.save(route), RouteDTO.class);
    }

    public RouteDTO update(RouteDTO routeDTO) {
        var entity = routeRepository.findById(routeDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setOrigin(routeDTO.getOrigin());
        entity.setDestination(routeDTO.getDestination());
        return parseObject(routeRepository.save(entity), RouteDTO.class);
    }

    public void delete(Long id) {
        var entity = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        routeRepository.delete(entity);
    }
}
