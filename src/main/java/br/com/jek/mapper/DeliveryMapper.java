package br.com.jek.mapper;

import br.com.jek.data.dto.DeliveryDTO;
import br.com.jek.model.Delivery;
import br.com.jek.model.Driver;
import br.com.jek.model.Route;
import br.com.jek.model.Vehicle;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DeliveryMapper {

    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static Delivery toEntity(DeliveryDTO deliveryDTO, Vehicle vehicle, Driver driver, Route route) {
        Delivery delivery = mapper.map(deliveryDTO, Delivery.class);
        delivery.setVehicle(vehicle);
        delivery.setDriver(driver);
        delivery.setRoute(route);
        return delivery;
    }

    public static DeliveryDTO toDTO(Delivery delivery) {
        DeliveryDTO deliveryDTO = mapper.map(delivery, DeliveryDTO.class);
        deliveryDTO.setVehicleId(delivery.getVehicle().getId());
        deliveryDTO.setDriverId(delivery.getDriver().getId());
        deliveryDTO.setRouteId(delivery.getRoute().getId());
        return deliveryDTO;
    }
}
