package br.com.jek.repository;

import br.com.jek.model.VehicleMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleMaintenanceRepository extends JpaRepository<VehicleMaintenance, Long> {

    @Query("SELECT vm FROM VehicleMaintenance vm WHERE vm.vehicle.licensePlate = :licensePlate")
    List<VehicleMaintenance> findByVehicleLicensePlate(@Param("licensePlate") String licensePlate);
}