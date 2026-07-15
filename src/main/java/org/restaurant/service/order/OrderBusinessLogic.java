package org.restaurant.service.order;

import org.restaurant.entity.MenuItem;
import org.restaurant.entity.Order;
import org.restaurant.entity.enums.OrderStatus;
import org.restaurant.exception.ConflictException;
import org.restaurant.repository.MenuItemRepository;
import org.restaurant.repository.OrderItemRepository;
import org.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderBusinessLogic {
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final MenuItemRepository menuItemRepo;

    public OrderBusinessLogic(OrderRepository orderRepo,
                              OrderItemRepository orderItemRepo,
                              MenuItemRepository menuItemRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.menuItemRepo = menuItemRepo;
    }

    public void menuItemAvailabilityCheck(MenuItem menuItem){
        if(!menuItem.isAvailable()){
            throw new ConflictException("Menu Item is not available for now");
        }
    }

    public void orderStatusUpdateCheck(OrderStatus current, OrderStatus updated){
        if(!current.canTransition(updated)){
            throw new ConflictException("Invalid Status Transition: Cannot transition to "
                    + updated + " status from " + current + " status.");
        }
    }
}
