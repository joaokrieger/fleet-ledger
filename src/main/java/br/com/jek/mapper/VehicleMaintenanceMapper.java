package br.com.jek.mapper;

import br.com.jek.data.dto.vehicleMaintenance.VehicleMaintenanceRequestDTO;
import br.com.jek.data.dto.vehicleMaintenance.VehicleMaintenanceResponseDTO;
import br.com.jek.model.Vehicle;
import br.com.jek.model.VehicleMaintenance;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class VehicleMaintenanceMapper {

    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static VehicleMaintenance toEntity(VehicleMaintenanceRequestDTO vehicleMaintenanceRequestDTO, Vehicle vehicle) {
        VehicleMaintenance vehicleMaintenance = mapper.map(vehicleMaintenanceRequestDTO, VehicleMaintenance.class);
        vehicleMaintenance.setVehicle(vehicle);
        return vehicleMaintenance;
    }

    public static VehicleMaintenanceResponseDTO toDTO(VehicleMaintenance vehicleMaintenance) {
        return mapper.map(vehicleMaintenance, VehicleMaintenanceResponseDTO.class);
    }
}
