package br.com.jek.service;

import br.com.jek.data.dto.DriverDTO;
import br.com.jek.exception.ResourceNotFoundException;
import br.com.jek.model.Driver;
import br.com.jek.repository.DriverRepository;
import static br.com.jek.mapper.ObjectMapper.parseObject;
import static br.com.jek.mapper.ObjectMapper.parseListObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public List<DriverDTO> findAll() {
        return parseListObjects(driverRepository.findAll(), DriverDTO.class);
    }

    public DriverDTO findById(Long id) {
        var entity = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return parseObject(entity, DriverDTO.class);
    }

    public DriverDTO create(DriverDTO driverDTO) {
        Driver driver = parseObject(driverDTO, Driver.class);
        return parseObject(driverRepository.save(driver), DriverDTO.class);
    }

    public DriverDTO update(DriverDTO driverDTO) {
        var entity = driverRepository.findById(driverDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setName(driverDTO.getName());
        entity.setLicenseNumber(driverDTO.getLicenseNumber());
        return parseObject(driverRepository.save(entity), DriverDTO.class);
    }

    public void delete(Long id) {
        var entity = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        driverRepository.delete(entity);
    }
}
