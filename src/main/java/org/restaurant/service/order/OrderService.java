package org.restaurant.service.order;

import org.restaurant.dto.order.OrderCreateRequest;
import org.restaurant.dto.order.OrderResponse;
import org.restaurant.dto.orderItem.OrderItemCreateRequest;
import org.restaurant.entity.MenuItem;
import org.restaurant.entity.Order;
import org.restaurant.entity.OrderItem;
import org.restaurant.exception.NotFoundException;
import org.restaurant.parser.OrderItemParser;
import org.restaurant.parser.OrderParser;
import org.restaurant.repository.MenuItemRepository;
import org.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepo;
    private final MenuItemRepository menuItemRepo;
    private final OrderNumberGenerator generator;
    private final OrderBusinessLogic orderLogic;

    public OrderService(OrderRepository orderRepo,
                        MenuItemRepository menuItemRepo,
                        OrderNumberGenerator generator,
                        OrderBusinessLogic orderLogic) {
        this.orderRepo = orderRepo;
        this.menuItemRepo = menuItemRepo;
        this.generator = generator;
        this.orderLogic = orderLogic;
    }

    public OrderResponse createOrder(OrderCreateRequest request){
        Order order = getProcessedOrder(request);
        order.setOrderNumber(generator.generateOrderNumber());

        Order saved = orderRepo.save(order);
        return OrderParser.toOrderResponseFromOrder(saved);
    }

    private MenuItem getMenuItemIfExist(int menuItemId){
        return menuItemRepo
                .findByIdAndIsDeletedFalse(menuItemId)
                .orElseThrow(
                        () -> new NotFoundException("Menu Item not found with id " + menuItemId));
    }

    private Order getProcessedOrder(OrderCreateRequest request){
        Order order = OrderParser.toOrderFromCreateRequest(request);
        for(OrderItemCreateRequest requestItem : request.getOrderItems()){
            MenuItem item = getMenuItemIfExist(requestItem.getMenuItemId());
            orderLogic.menuItemAvailabilityCheck(item);

            OrderItem orderItem = OrderItemParser.toOrderItemFromCreateRequest(requestItem);
            orderItem.setMenuItem(item);
            orderItem.setItemPrice(item.getPrice());

            order.addItem(orderItem);
        }

        return order;
    }
}
