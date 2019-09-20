package com.ecom.ecom.repository;

import com.ecom.ecom.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<Payment,Long> {
}
