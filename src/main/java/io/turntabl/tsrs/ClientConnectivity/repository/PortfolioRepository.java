package io.turntabl.tsrs.ClientConnectivity.repository;

import io.turntabl.tsrs.ClientConnectivity.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {


}
