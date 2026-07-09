package org.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.restaurant.entity.enums.MenuCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu_items")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column(nullable = false, updatable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuCategory category;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
