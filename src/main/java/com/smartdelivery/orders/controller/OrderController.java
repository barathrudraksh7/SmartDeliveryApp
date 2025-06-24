package com.smartdelivery.orders.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartdelivery.orders.model.Order;
import com.smartdelivery.orders.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders") //-- base URL for all endpoints
public class OrderController {
    private final OrderService orderService;

    //Constructor based injection of OrderService
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    //endpoint to create a new order
    @PostMapping //--not specifying the endpoint so base endpoint will be routed to this controller with a POST request
    public ResponseEntity<String> createOrder(@Valid @RequestBody Order order){
        orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created successfully..!!"); 
    }

    @GetMapping //--not specifying the endpoint so base endpoint will be routed to this controller with a GET request
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/getOrder/{name}")
    public ResponseEntity<List<Order>> getOrderByName(@PathVariable String name){
        return ResponseEntity.ok(orderService.getOrderByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @Valid @RequestBody Order order){
        orderService.updateOrder(id, order);
        return ResponseEntity.status(HttpStatus.OK).body("Record Updated successfully..!!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record deleted successfully..!!");
    }



}
