package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.ServiceImpl.PaymentService;
import com.razorpay.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestParam double amount) {
        try {
            Order order = paymentService.createOrder(amount);
            return ResponseEntity.ok(order.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Payment error: " + e.getMessage());
        }
    }
}