package io.turntabl.tsrs.repository;

import io.turntabl.tsrs.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}