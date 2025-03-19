package br.com.jek.mapper;

import br.com.jek.data.dto.delivery.DeliveryRequestDTO;
import br.com.jek.data.dto.delivery.DeliveryResponseDTO;
import br.com.jek.model.Delivery;
import br.com.jek.model.Driver;
import br.com.jek.model.Route;
import br.com.jek.model.Vehicle;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DeliveryMapper {

    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static Delivery toEntity(DeliveryRequestDTO deliveryRequestDTO, Vehicle vehicle, Driver driver, Route route) {
        Delivery delivery = mapper.map(deliveryRequestDTO, Delivery.class);
        delivery.setVehicle(vehicle);
        delivery.setDriver(driver);
        delivery.setRoute(route);
        return delivery;
    }

    public static DeliveryResponseDTO toDTO(Delivery delivery) {
        DeliveryResponseDTO deliveryResponseDTO = mapper.map(delivery, DeliveryResponseDTO.class);
        return deliveryResponseDTO;
    }
}
