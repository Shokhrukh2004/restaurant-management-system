package org.restaurant.feature.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_sequences")
@Getter @Setter
@NoArgsConstructor
public class OrderSequence {

    @Id
    private String sequenceName = "order_number";

    @Column(nullable = false)
    private long nextValue = 1;
}
