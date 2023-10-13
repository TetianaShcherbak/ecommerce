package com.example.ecommerce.controller;


import com.example.ecommerce.enums.ShipmentStatus;
import com.example.ecommerce.exceptions.AccessDeniedException;
import com.example.ecommerce.exceptions.OrderNotFoundException;
import com.example.ecommerce.exceptions.UserNotFoundException;
import com.example.ecommerce.model.Item;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.AuthService;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final AuthService authService;


    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("token") String token)
            throws UserNotFoundException, IllegalAccessException {
        String userId = authService.getUserId(token);
        User user = userService.getUser(userId);
        List<Order> orders = orderService.getAllOrders(user);
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @GetMapping("/shipment-status")
    public ResponseEntity<List<Order>> getAllOrdersByShipmentStatus(@RequestParam("token") String token, @RequestBody ShipmentStatus status)
            throws UserNotFoundException, IllegalAccessException {
        String userId = authService.getUserId(token);
        User user = userService.getUser(userId);
        List<Order> orders = orderService.getOrdersByShipmentStatus(user, status);
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @GetMapping("/shipment-status")
    public ResponseEntity<List<Order>> getAllOrdersByShipmentStatus(@RequestParam("token") String token, @RequestBody List<ShipmentStatus> statusList)
            throws UserNotFoundException, IllegalAccessException {
        String userId = authService.getUserId(token);
        User user = userService.getUser(userId);
        List<Order> orders = orderService.getOrdersByShipmentStatus(user, statusList);
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrderById(@RequestParam("token") String token, @PathVariable String orderId)
            throws IllegalAccessException, UserNotFoundException, OrderNotFoundException, AccessDeniedException {
        String userId = authService.getUserId(token);
        User user = userService.getUser(userId);
        Order order = orderService.getOrderById(user, orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @PostMapping("order/add")
    public ResponseEntity<String> createOrder(@RequestParam("token") String token, @RequestBody List<Item> items)
            throws IllegalAccessException, UserNotFoundException {
        String userId = authService.getUserId(token);
        User user = userService.getUser(userId);
        orderService.createOrder(user, items);
        return new ResponseEntity<>("created", HttpStatus.OK);
    }

    @PutMapping("order/{orderId}")
    public ResponseEntity<String> updateOrder(@RequestParam("token") String token, @PathVariable String orderId, @RequestBody List<Item> items)
            throws IllegalAccessException, UserNotFoundException, OrderNotFoundException, AccessDeniedException {
        String userId = authService.getUserId(token);
        User user = userService.getUser(userId);
        orderService.updateOrder(user, orderId, items);
        return new ResponseEntity<>("created", HttpStatus.OK);
    }


    @PutMapping("order/{orderId}")
    public ResponseEntity<String> updateOrder(@RequestParam("token") String token, @PathVariable String orderId, @RequestBody ShipmentStatus status)
            throws IllegalAccessException, UserNotFoundException, OrderNotFoundException, AccessDeniedException {
        String userId = authService.getUserId(token);
        User user = userService.getUser(userId);
        orderService.updateOrder(user, orderId, status);
        return new ResponseEntity<>("created", HttpStatus.OK);
    }



    @DeleteMapping("order/{orderId}")
    public ResponseEntity<String> deleteOrder(@RequestParam("token") String token, @PathVariable String orderId)
            throws IllegalAccessException, UserNotFoundException, OrderNotFoundException, AccessDeniedException {
        String userId = authService.getUserId(token);
        User user = userService.getUser(userId);
        orderService.deleteOrderById(user, orderId);
        return new ResponseEntity<>("created", HttpStatus.OK);
    }



}
