package com.ecom.ecom.service;

import com.ecom.ecom.exception.DoesNotExistsException;
import com.ecom.ecom.exception.InsufficientAmountException;
import com.ecom.ecom.model.Customer;
import com.ecom.ecom.model.ProductOrder;
import com.ecom.ecom.model.Payment;
import com.ecom.ecom.repository.CustomerRepo;
import com.ecom.ecom.repository.OrderRepo;
import com.ecom.ecom.repository.PaymentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepo paymentRepo;
    private CustomerRepo customerRepo;
    private OrderRepo orderRepo;

    public PaymentService(PaymentRepo paymentRepo, CustomerRepo customerRepo, OrderRepo orderRepo) {
        this.paymentRepo = paymentRepo;
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
    }

    public ResponseEntity getAllPayments() {
        return ResponseEntity.status(200).body(paymentRepo.findAll());
    }

    public ResponseEntity<Payment> savePayment(Payment payment) {
        //check the customer.
        Optional<Customer> optionalCustomer = customerRepo.findById(payment.getCustomerId());
        if(!optionalCustomer.isPresent()){
            throw new DoesNotExistsException("Customer with this id "
                    +payment.getCustomerId()+" does not exists");
        }

        //check for productOrder
        Optional<ProductOrder> optionalOrder = orderRepo.findById(payment.getOrderId());
        if(!optionalOrder.isPresent()){
            throw new DoesNotExistsException("ProductOrder with this id "
                    +payment.getOrderId()+" does not exists");
        }

        //check the amount.
        if(optionalOrder.get().getOrderPrice() != payment.getAmount()){
            throw new InsufficientAmountException("Payment amount is insufficient for this productOrder."
                    +"Expected amount "+optionalOrder.get().getOrderPrice());
        }

        ProductOrder productOrder = optionalOrder.get();
        productOrder.setDelivered(true);
        productOrder.setPending(false);

        orderRepo.save(productOrder);
        return ResponseEntity.status(200).body(payment);
    }
}
