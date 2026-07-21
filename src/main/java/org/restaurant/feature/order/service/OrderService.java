package org.restaurant.feature.order.service;

import org.restaurant.feature.order.dto.OrderCreateRequest;
import org.restaurant.feature.order.dto.OrderResponse;
import org.restaurant.feature.order.dto.OrderItemCreateRequest;
import org.restaurant.feature.menu.entity.MenuItem;
import org.restaurant.feature.order.entity.Order;
import org.restaurant.feature.order.entity.OrderItem;
import org.restaurant.feature.order.enums.OrderItemStatus;
import org.restaurant.feature.order.enums.OrderStatus;
import org.restaurant.feature.order.business.OrderBusinessLogic;
import org.restaurant.feature.order.parser.OrderItemParser;
import org.restaurant.feature.order.parser.OrderParser;
import org.restaurant.feature.order.repository.OrderRepository;
import org.restaurant.shared.util.order.EntityFinder;
import org.restaurant.shared.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepo;
    private final EntityFinder entityFinder;
    private final OrderNumberGenerator generator;
    private final OrderBusinessLogic orderLogic;

    public OrderService(OrderRepository orderRepo,
                        EntityFinder entityFinder,
                        OrderNumberGenerator generator,
                        OrderBusinessLogic orderLogic) {
        this.orderRepo = orderRepo;
        this.entityFinder = entityFinder;
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
                .toOrderResponseFromOrder(entityFinder.getOrderIfExists(orderId));
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

        Order order = entityFinder.getOrderIfExists(orderId);
        orderLogic.orderStatusUpdateCheck(order.getOrderStatus(), status);

        order.setOrderStatus(status);
        setCompletedDateIfStatusDelivered(order);
        updateOrderItemStatusToReady(order);

        Order updated = orderRepo.save(order);
        return OrderParser
                .toOrderResponseFromOrder(updated);
    }

    private void setCompletedDateIfStatusDelivered(Order order){
        if(order.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            order.setCompletedAt(LocalDateTime.now());
        }
    }

    private Order getProcessedOrder(OrderCreateRequest request){
        Order order = OrderParser
                .toOrderFromCreateRequest(request);

        for(OrderItemCreateRequest requestItem : request.getOrderItems()){
            MenuItem item = entityFinder
                    .getMenuItemIfExist(requestItem.getMenuItemId());

            orderLogic.menuItemAvailabilityCheck(item);

            OrderItem orderItem = OrderItemParser
                    .toOrderItemFromCreateRequest(requestItem);

            orderItem.setMenuItem(item);
            orderItem.setItemPrice(item.getPrice());

            order.addItem(orderItem);
        }

        return order;
    }

    private void updateOrderItemStatusToReady(Order order){
        for(OrderItem orderItem : order.getOrderItems()){
            if(orderItem.getOrderItemStatus().equals(OrderItemStatus.PENDING)){
                orderItem.setOrderItemStatus(OrderItemStatus.READY);
            }
        }

        orderRepo.save(order);
    }
}
