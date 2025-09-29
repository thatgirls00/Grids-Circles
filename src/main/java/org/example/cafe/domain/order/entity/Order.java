package org.example.cafe.domain.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.cafe.domain.member.entity.Member;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("주문상태는 14:00을 기준으로 상태가 바뀜")
    @Enumerated(EnumType.STRING)
    @Column(name = "orderState", length = 10, nullable = false)
    private OrderState orderState;

    @Column(name = "totalPrice", nullable = false)
    private int totalPrice;

    @Column(name = "orderDate", nullable = false)
    private LocalDate orderDate;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "postalCode", length = 20, nullable = false)
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false) // FK
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
        updateTotalPrice();
    }

    public void updateTotalPrice() {
        this.totalPrice = orderItems.stream()
                .mapToInt(OrderItem::getOrderPrice)
                .sum();
    }

    public static Order createOrder(Member member, String address, String postalCode, List<OrderItem> orderItems) {
        Order order = Order.builder()
                .member(member)
                .orderDate(LocalDate.now())
                .orderState(OrderState.READY)
                .address(address)
                .postalCode(postalCode)
                .totalPrice(0) // 초기값
                .build();

        for (OrderItem item : orderItems) {
            order.addOrderItem(item);
        }

        return order;
    }
}
