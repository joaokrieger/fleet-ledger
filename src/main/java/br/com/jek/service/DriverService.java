package br.com.jek.service;

import br.com.jek.controller.DriverController;
import br.com.jek.data.dto.DriverDTO;
import br.com.jek.exception.ResourceNotFoundException;
import br.com.jek.model.Driver;
import br.com.jek.repository.DriverRepository;
import static br.com.jek.mapper.ObjectMapper.parseObject;
import static br.com.jek.mapper.ObjectMapper.parseListObjects;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public List<DriverDTO> findAll() {
        List<DriverDTO> drivers = parseListObjects(driverRepository.findAll(), DriverDTO.class);
        drivers.forEach(this::addHateoasLinks);
        return drivers;
    }

    public DriverDTO findById(Long id) {
        var entity = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        DriverDTO driverDTO = parseObject(entity, DriverDTO.class);
        addHateoasLinks(driverDTO);
        return driverDTO;
    }

    public DriverDTO create(DriverDTO driver) {
        var entity = parseObject(driver, Driver.class);
        DriverDTO driverDTO = parseObject(driverRepository.save(entity), DriverDTO.class);
        addHateoasLinks(driverDTO);
        return driverDTO;
    }

    public DriverDTO update(DriverDTO driver) {
        var entity = driverRepository.findById(driver.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setName(driver.getName());
        entity.setLicenseNumber(driver.getLicenseNumber());

        DriverDTO driverDTO = parseObject(driverRepository.save(entity), DriverDTO.class);
        addHateoasLinks(driverDTO);
        return driverDTO;
    }

    public void delete(Long id) {
        var entity = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        driverRepository.delete(entity);
    }

    private void addHateoasLinks(DriverDTO driverDTO){
        driverDTO.add(linkTo(methodOn(DriverController.class).getDriverById(driverDTO.getId())).withSelfRel().withType("GET"));
        driverDTO.add(linkTo(methodOn(DriverController.class).getAllDrivers()).withRel("getAllDrivers").withType("GET"));
        driverDTO.add(linkTo(methodOn(DriverController.class).createDriver(driverDTO)).withRel("createDriver").withType("POST"));
        driverDTO.add(linkTo(methodOn(DriverController.class).updateDriver(driverDTO)).withRel("updateDriver").withType("PUT"));
        driverDTO.add(linkTo(methodOn(DriverController.class).deleteDriver(driverDTO.getId())).withRel("deleteDriver").withType("PUT"));
    }
}
