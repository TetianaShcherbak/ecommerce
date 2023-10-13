package com.example.ecommerce.service;

import com.example.ecommerce.enums.ShipmentStatus;
import com.example.ecommerce.enums.UserRole;
import com.example.ecommerce.exceptions.AccessDeniedException;
import com.example.ecommerce.exceptions.OrderNotFoundException;
import com.example.ecommerce.model.Item;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;


    // ------------- GET ------------------------ //
    public List<Order> getAllOrders(User user){
        if (user.getRole().equals(UserRole.ADMIN)){
            return repository.findAll();
        }
        return getOrdersByUserId(user);
    }

    public List<Order> getOrdersByUserId(User user){
        return repository.findAllByUserId(user.getId());
    }
    public List<Order> getOrdersByShipmentStatus(User user, ShipmentStatus status){
        return getOrdersByUserId(user).stream()
                .filter(order -> order.getShipmentStatus().equals(status))
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByShipmentStatus(User user, List<ShipmentStatus> statusList){
        List<Order> matchingConditionOrders = new ArrayList<>();

        statusList.forEach(status -> {
            List<Order> orders = getOrdersByShipmentStatus(user, status);
            matchingConditionOrders.addAll(orders);
        });

        return matchingConditionOrders;
    }

    public Order getOrderById(User user, String orderId)
            throws OrderNotFoundException, AccessDeniedException {
        if (user.getRole().equals(UserRole.ADMIN)){
            return getOrderById(orderId);
        }
        List<Order> userOrders = getOrdersByUserId(user);
        List<Order> ordersMatchesId = userOrders.stream().filter(record -> record.getId().equals(orderId)).toList();
        if (ordersMatchesId.isEmpty()){
            // assumption: somebody might try to steal order data by passing stranger's order id
            throw new AccessDeniedException();
        }
        return ordersMatchesId.get(0);
    }

    // ------------- POST ------------------------ //
    public void createOrder(User user, List<Item> items){
        Order order = new Order(user.getId(), items);
        repository.insert(order);
    }

    // ------------- PUT ------------------------ //

    public void updateOrder(User user, String orderId, List<Item> items)
            throws OrderNotFoundException, AccessDeniedException {
        if (user.getRole().equals(UserRole.ADMIN)){
            updateItemsList(orderId, items);
            return;
        }

        Order order = getOrderById(orderId);
        if (!order.getUserId().equals(user.getId())) {
            // somebody doing smth nasty
            throw new AccessDeniedException();
        }

        updateItemsList(orderId, items);
    }

    public void updateOrder(User user, String orderId, ShipmentStatus status)
            throws OrderNotFoundException, AccessDeniedException {
        if (user.getRole().equals(UserRole.ADMIN)){
            updateShipmentStatus(orderId, status);
            return;
        }

        Order order = getOrderById(orderId);
        if (!order.getUserId().equals(user.getId())) {
            // somebody doing smth nasty
            throw new AccessDeniedException();
        }

        updateShipmentStatus(orderId, status);
    }


    // ------------- DELETE ------------------------ //

    public void deleteOrderById(User user, String orderId)
            throws AccessDeniedException, OrderNotFoundException {
        if (user.getRole().equals(UserRole.USER)){
            throw new AccessDeniedException();
        }
        Order order = getOrderById(orderId);

        repository.delete(order);
    }

    // ------------- PRIVATE METHODS ------------------------ //

    private Order getOrderById(String orderId) throws OrderNotFoundException {
        return repository.findById(orderId).get();
    }

    private void updateItemsList(String orderId, List<Item> items) throws OrderNotFoundException {
        Order order = getOrderById(orderId);

        BigDecimal newPrice = items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(newPrice);
        order.setItems(items);
        order.setUpdatedDate(LocalDateTime.now());

        repository.save(order);
    }

    private void updateShipmentStatus(String orderId, ShipmentStatus status) throws OrderNotFoundException {
        Order order = getOrderById(orderId);

        order.setShipmentStatus(status);
        order.setUpdatedDate(LocalDateTime.now());

        repository.save(order);
    }



}
