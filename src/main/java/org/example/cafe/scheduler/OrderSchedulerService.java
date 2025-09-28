package org.example.cafe.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cafe.domain.order.entity.Order;
import org.example.cafe.domain.order.entity.OrderState;
import org.example.cafe.domain.order.repository.OrderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderSchedulerService {

    private final OrderRepository orderRepository;

    /**
     * 매일 오후 2시에 READY → SHIPPING 상태로 변경
     */

    @Scheduled(cron = "0 0 14 * * *", zone = "Asia/Seoul")
    public void scheduledUpdateOrdersToShipping() {
        updateOrdersToShipping();
    }

    public void updateOrdersToShipping() {
        List<Order> readyOrders = orderRepository.findByOrderState(OrderState.READY);
        readyOrders.forEach(order -> order.setOrderState(OrderState.SHIPPING));
        orderRepository.saveAll(readyOrders);
    }
}
