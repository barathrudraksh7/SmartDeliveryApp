package com.smartdelivery.orders.service;

import com.smartdelivery.orders.exception.CustomException;
import com.smartdelivery.orders.model.Order;
import com.smartdelivery.orders.repository.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    
    //declaring the repo object as final
    private final OrderRepo orderRepo;

    //constructor based injection
    public OrderService(OrderRepo orderRepo){
        this.orderRepo = orderRepo;
    }

    //create a new order and save it to the database
    public Order createOrder(Order order){
        order.setStatus("Created");
        return orderRepo.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }

    public List<Order> getOrderByName(String name){
        return orderRepo.findByCustomerName(name);
    }

    public Order updateOrder(Long id, Order updatedOrder){
        //findById returns order that matches the ID, for that order the map function helps replace the 'order' 
        //with the order returned from return orderRepo.save(order)
        return orderRepo.findById(id).map(order -> {
        order.setCustomerName(updatedOrder.getCustomerName());
        order.setProduct(updatedOrder.getProduct());
        order.setQuantity(updatedOrder.getQuantity());
        order.setStatus(order.getStatus());
        return orderRepo.save(order);
    }).orElseThrow(() -> new RuntimeException("Order not found"));

    }

    public void deleteOrder(Long id){
        if(!orderRepo.existsById(id)){
            throw new CustomException("The order does not exist!!");
        }else{
            orderRepo.deleteById(id);
        }
    }

}
