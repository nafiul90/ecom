package com.ecom.ecom.repository;

import com.ecom.ecom.model.ProductOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<ProductOrder,Long> {
    public Iterable<ProductOrder> getOrdersByCustomerId(long id);
}
