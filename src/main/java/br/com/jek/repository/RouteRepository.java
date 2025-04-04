package br.com.jek.repository;

import br.com.jek.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("SELECT DISTINCT d.route FROM Delivery d WHERE d.arrivalTime IS NULL")
    List<Route> findActiveRoutes();
}