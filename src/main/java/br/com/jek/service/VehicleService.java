package br.com.jek.service;

import br.com.jek.data.dto.VehicleDTO;
import static br.com.jek.mapper.ObjectMapper.parseObject;
import static br.com.jek.mapper.ObjectMapper.parseListObjects;

import br.com.jek.exception.ResourceNotFoundException;
import br.com.jek.model.Vehicle;
import br.com.jek.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleDTO> findAll() {
        return parseListObjects(vehicleRepository.findAll(), VehicleDTO.class);
    }

    public VehicleDTO findById(Long id) {
        var entity = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return parseObject(entity, VehicleDTO.class);
    }

    public VehicleDTO create(VehicleDTO vehicleDTO) {
        var entity = parseObject(vehicleDTO, Vehicle.class);
        return parseObject(vehicleRepository.save(entity), VehicleDTO.class);
    }

    public VehicleDTO update(VehicleDTO vehicleDTO) {
        var entity = vehicleRepository.findById(vehicleDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setBrand(vehicleDTO.getBrand());
        entity.setLicensePlate(vehicleDTO.getLicensePlate());
        entity.setModel(vehicleDTO.getModel());
        entity.setYear(vehicleDTO.getYear());

        return parseObject(vehicleRepository.save(entity), VehicleDTO.class);
    }

    public void delete(Long id) {
        var entity = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        vehicleRepository.delete(entity);
    }
}
