package org.restaurant.feature.menu.repository;


import org.restaurant.feature.menu.entity.MenuItem;
import org.restaurant.feature.menu.enums.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    List<MenuItem> findByIsDeletedFalse();

    List<MenuItem> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name);

    Optional<MenuItem> findByNameIgnoreCaseAndIsDeletedFalse(String name);

    Optional<MenuItem> findByIdAndIsDeletedFalse(int id);

    List<MenuItem> findByCategoryAndIsDeletedFalse(MenuCategory category);

    List<MenuItem> findByIsDeletedTrue();

    List<MenuItem> findByIsAvailableAndIsDeletedFalse(boolean isAvailable);
}
