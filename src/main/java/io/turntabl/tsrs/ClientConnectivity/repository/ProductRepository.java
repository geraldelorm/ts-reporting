package io.turntabl.tsrs.ClientConnectivity.repository;

import io.turntabl.tsrs.ClientConnectivity.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}