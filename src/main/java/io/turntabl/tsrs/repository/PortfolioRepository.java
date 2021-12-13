package io.turntabl.tsrs.repository;

import io.turntabl.tsrs.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {


}
