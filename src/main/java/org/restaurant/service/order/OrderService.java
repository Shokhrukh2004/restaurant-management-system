package org.restaurant.service.order;

import jakarta.validation.Valid;
import org.restaurant.dto.order.OrderCreateRequest;
import org.restaurant.dto.order.OrderResponse;
import org.restaurant.dto.orderItem.OrderItemCreateRequest;
import org.restaurant.entity.MenuItem;
import org.restaurant.entity.Order;
import org.restaurant.entity.OrderItem;
import org.restaurant.entity.enums.OrderStatus;
import org.restaurant.exception.NotFoundException;
import org.restaurant.parser.OrderItemParser;
import org.restaurant.parser.OrderParser;
import org.restaurant.repository.MenuItemRepository;
import org.restaurant.repository.OrderRepository;
import org.restaurant.service.menuItem.MenuItemService;
import org.restaurant.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    public OrderResponse getOrderById(int orderId){
        Validator.validatePositiveInt(orderId, "Order ID");
        return OrderParser
                .toOrderResponseFromOrder(getOrderIfExists(orderId));
    }

    public List<OrderResponse> getAllOrders(){
        return orderRepo
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .map(OrderParser::toOrderResponseFromOrder)
                .toList();
    }

    public List<OrderResponse> getOrdersByStatus(OrderStatus status){
        return orderRepo
                .findByOrderStatus(status)
                .stream()
                .map(OrderParser::toOrderResponseFromOrder)
                .toList();
    }

    public OrderResponse updateOrderStatus(int orderId, OrderStatus status){
        Validator.validatePositiveInt(orderId, "Order ID");

        Order order = getOrderIfExists(orderId);
        orderLogic.orderStatusUpdateCheck(order.getOrderStatus(), status);

        order.setOrderStatus(status);
        setCompletedDateIfStatusDelivered(order);

        Order updated = orderRepo.save(order);
        return OrderParser.toOrderResponseFromOrder(updated);
    }

    private MenuItem getMenuItemIfExist(int menuItemId){

        return menuItemRepo
                .findByIdAndIsDeletedFalse(menuItemId)
                .orElseThrow(
                        () -> new NotFoundException("Menu Item not found with ID " + menuItemId));
    }

    private Order getOrderIfExists(int orderId){

        return orderRepo
                .findById(orderId)
                .orElseThrow(
                        () -> new NotFoundException("Order not found with id " + orderId));
    }

    private void setCompletedDateIfStatusDelivered(Order order){
        if(order.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            order.setCompletedAt(LocalDateTime.now());
        }
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
