package org.restaurant.repository;


import org.restaurant.entity.enums.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    List<MenuItem> findByIsDeletedFalse();

    List<MenuItem> findByNameContainingAndIgnoreCaseAndIsDeletedFalse(String name);

    List<MenuItem> findByMenuCategoryAndIsDeletedFalse(MenuCategory category);

    List<MenuItem> findByIsDeletedTrue();

    List<MenuItem> findByIsAvailableAndIsDeletedFalse(boolean isAvailable);
}
