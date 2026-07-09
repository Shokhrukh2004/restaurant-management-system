package org.restaurant.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.restaurant.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_number", nullable = false, updatable = false, unique = true)
    private String orderNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "completed_at", nullable = false,  updatable = false)
    private LocalDateTime completedAt;

    public void addItem(OrderItem orderItem){
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }

    public void removeItem(OrderItem orderItem){
        orderItem.setOrder(null);
        orderItems.remove(orderItem);
    }

    public BigDecimal getTotalPrice(){
        return orderItems.stream()
                .map(item -> item.getItemPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
