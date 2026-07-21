package org.restaurant.feature.order.service;

import org.restaurant.feature.order.dto.OrderItemResponse;
import org.restaurant.feature.order.entity.Order;
import org.restaurant.feature.order.entity.OrderItem;
import org.restaurant.feature.order.enums.OrderItemStatus;
import org.restaurant.feature.order.enums.OrderStatus;
import org.restaurant.feature.order.parser.OrderItemParser;
import org.restaurant.feature.order.repository.OrderItemRepository;
import org.restaurant.feature.order.business.OrderItemBusinessLogic;
import org.restaurant.feature.order.repository.OrderRepository;
import org.restaurant.shared.util.order.EntityFinder;
import org.restaurant.shared.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepo;
    private final OrderItemBusinessLogic orderItemLogic;
    private final EntityFinder entityFinder;
    private final OrderRepository orderRepo;

    public OrderItemService(OrderItemRepository orderItemRepo,
                            OrderItemBusinessLogic orderItemLogic,
                            EntityFinder entityFinder,
                            OrderRepository orderRepo) {
        this.orderItemRepo = orderItemRepo;
        this.orderItemLogic = orderItemLogic;
        this.entityFinder = entityFinder;
        this.orderRepo = orderRepo;
    }

    public OrderItemResponse updateQuantity(int itemId, int quantity){
        Validator.validatePositiveInt(itemId, "Item Id");
        Validator.validatePositiveInt(quantity, "Quantity");

        OrderItem item = entityFinder.getOrderItemIfExists(itemId);
        orderItemLogic.orderNotDeliveredCheck(
                item.getOrder().getOrderStatus());

        item.setQuantity(quantity);
        OrderItem updated = orderItemRepo.save(item);

        return OrderItemParser
                .toOrderItemResponseFromOrderItem(updated);
    }

    public OrderItemResponse updateOrderItemStatus(int itemId, OrderItemStatus orderItemStatus){
        Validator.validatePositiveInt(itemId, "Order Id");

        OrderItem item = entityFinder.getOrderItemIfExists(itemId);
        Order order = item.getOrder();

        orderItemLogic
                .orderItemStatusUpdateCheck(item.getOrderItemStatus(), orderItemStatus);

        item.setOrderItemStatus(orderItemStatus);
        OrderItem updated = orderItemRepo.save(item);

        updateOrderStatusIfReady(order);
        orderRepo.save(order);

        return OrderItemParser
                .toOrderItemResponseFromOrderItem(updated);
    }

    private void updateOrderStatusIfReady(Order order){
        boolean isOrderReady = orderItemLogic
                .orderTransitionToReadyCheck(order.getOrderItems());

        if(isOrderReady){
            order.setOrderStatus(OrderStatus.READY);
        }
    }



}
