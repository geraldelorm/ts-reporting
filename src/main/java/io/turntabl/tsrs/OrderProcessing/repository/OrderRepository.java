package io.turntabl.tsrs.OrderProcessing.repository;


import io.turntabl.tsrs.OrderProcessing.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
