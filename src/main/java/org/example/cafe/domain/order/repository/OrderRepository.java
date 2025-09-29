package org.example.cafe.domain.order.repository;

import org.example.cafe.domain.order.entity.Order;
import org.example.cafe.domain.order.entity.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // READY 상태인 주문만 조회
    List<Order> findByOrderState(OrderState orderState);
}
