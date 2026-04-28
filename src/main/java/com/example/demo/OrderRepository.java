package com.example.demo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<FoodOrder, String> {
    
    @Modifying
    @Query("INSERT INTO orders (order_id, customer_name, restaurant, amount, status) VALUES (:id, :name, :restaurant, :amount, :status)")
    void forceInsert(@Param("id") String id, 
                     @Param("name") String name, 
                     @Param("restaurant") String restaurant, 
                     @Param("amount") double amount, 
                     @Param("status") String status);
                    
    // Sort by Amount Ascending
    @Query("SELECT * FROM orders ORDER BY amount ASC")
    List<FoodOrder> findAllSortedByAmount();

    // Filter Premium Orders (> 1000)
    @Query("SELECT * FROM orders WHERE amount > 1000")
    List<FoodOrder> findPremiumOrders();

    // Sort by ID Ascending
    @Query("SELECT * FROM orders ORDER BY order_id ASC")
    List<FoodOrder> findAllSortedById();
}