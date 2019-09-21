package com.ecom.ecom.service;

import com.ecom.ecom.exception.DoesNotExistsException;
import com.ecom.ecom.exception.InsufficientQuantityException;
import com.ecom.ecom.model.*;
import com.ecom.ecom.repository.CustomerRepo;
import com.ecom.ecom.repository.OrderRepo;
import com.ecom.ecom.repository.ProductVariantRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    private OrderRepo orderRepo;
    private CustomerRepo customerRepo;
    private ProductVariantRepo productVariantRepo;

    public OrderService(OrderRepo orderRepo, CustomerRepo customerRepo, ProductVariantRepo productVariantRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.productVariantRepo = productVariantRepo;
    }

    public ResponseEntity<Iterable<ProductOrder>> getAllOrders() {
        return ResponseEntity.ok().body(orderRepo.findAll());
    }

    public ResponseEntity<Optional<ProductOrder>> getOrderById(long id) {
        Optional<ProductOrder> optionalOrder = orderRepo.findById(id);
        if (!optionalOrder.isPresent()) {
            throw new DoesNotExistsException("ProductOrder with this id " + id + " does not exists");
        }
        return ResponseEntity.status(200).body(optionalOrder);
    }


    public ResponseEntity<Iterable<ProductOrder>> getOrdersByCustomerId(long id) {
        return ResponseEntity.status(200).body(orderRepo.getOrdersByCustomerId(id));
    }

    public ResponseEntity<ProductOrder> saveOrder(ProductOrder productOrder) {
        Optional<Customer> optionalCustomer = customerRepo.findById(productOrder.getCustomerId());
        if (!optionalCustomer.isPresent()) {
            throw new DoesNotExistsException("Customer with this id " + productOrder.getCustomerId() + " is not exists");
        }

        //check all products and their quantities.
        for (ProductVariantBundle p : productOrder.getProductVariantBundleList()) {
            Optional<ProductVariant> optionalProduct = productVariantRepo.findById(p.getVariantId());

            //check if the product does not exists.
            if (!optionalProduct.isPresent()) {
                throw new DoesNotExistsException("Product with this id "
                        + p.getVariantId() + " does not exists");
            }

            //check if the quantity is insufficient.
            if (optionalProduct.get().getQuantity() < p.getQuantity()) {
                throw new InsufficientQuantityException("Product with id "
                        + p.getVariantId() + " has not expected quantity."
                        + "Stock = " + optionalProduct.get().getQuantity());
            }
        }

        //update product variant quantities after productOrder.
        //add list all the variants for calculate productOrder prices and discounts
        List<ProductVariant> productVariantList = new ArrayList<>();
        for (ProductVariantBundle p : productOrder.getProductVariantBundleList()) {
            Optional<ProductVariant> optionalProduct = productVariantRepo.findById(p.getVariantId());

            ProductVariant productVariant = optionalProduct.get();
            productVariant.setQuantity(productVariant.getQuantity() - p.getQuantity());
            productVariantRepo.save(productVariant);
            productVariantList.add(productVariant);
        }

        //make the productOrder properly by calculating.
        productOrder = makeOrder(productOrder, productVariantList);

        //save productOrder.
        orderRepo.save(productOrder);
        return ResponseEntity.status(200).body(productOrder);
    }


    private ProductOrder makeOrder(ProductOrder productOrder, List<ProductVariant> productVariantList) {

        //backup the original bundle
        List<ProductVariantBundle> copyProduductVariantBundleList = new ArrayList<>();
        productOrder.getProductVariantBundleList().forEach(e->copyProduductVariantBundleList.add(new ProductVariantBundle(e.getVariantId(),e.getQuantity())));


        //check for package pricing
        double packagePriceCount = 0;
        int i = 0;
        for (ProductVariant p : productVariantList) {
            sort(p.getPackagePrice());
            long orderedQuantity = productOrder.getProductVariantBundleList().get(i).getQuantity();
            for(PackagePrice pp:p.getPackagePrice()){
                if(pp.getPiece() <= orderedQuantity){
                    long a = orderedQuantity / pp.getPiece();
                    packagePriceCount+=a*pp.getPrice();
                    productOrder.getProductVariantBundleList().get(i).setQuantity(orderedQuantity%pp.getPiece());
                    orderedQuantity = orderedQuantity%pp.getPiece();
                }
            }
            i++;
        }



        double totalDiscountPer = calculateTotalDiscountPercentage(productOrder.getProductVariantBundleList(),
                productVariantList);

        double totalDiscountAm = calculateTotalDiscountAmount(productOrder.getProductVariantBundleList(),
                productVariantList);

        double totalPrice = calculateTotalPrice(productOrder.getProductVariantBundleList(),
                productVariantList) + packagePriceCount;

        double totalDiscount = totalDiscountPer + totalDiscountAm;
        double orderPrice = totalPrice - totalDiscount;

        productOrder = new ProductOrder(productOrder.getCustomerId(), copyProduductVariantBundleList,
                totalPrice, totalDiscountAm, totalDiscountPer, totalDiscount, orderPrice,
                true, false);

        return productOrder;
    }


    public double calculateTotalDiscountPercentage(List<ProductVariantBundle> productVariantBundleList,
                                                   List<ProductVariant> productVariantList) {
        double discount = 0;
        int i = 0;
        for (ProductVariantBundle e : productVariantBundleList) {
            discount += e.getQuantity() * ((productVariantList.get(i).getDiscountPercentage()*productVariantList.get(i).getSalePrice())/100);
            i++;
        }
        ;

        return discount;
    }

    public double calculateTotalDiscountAmount(List<ProductVariantBundle> productVariantBundleList,
                                               List<ProductVariant> productVariantList) {
        double discount = 0;
        int i = 0;
        for (ProductVariantBundle e : productVariantBundleList) {
            discount += e.getQuantity() * productVariantList.get(i).getDiscountAmount();
            i++;
        }
        ;

        return discount;
    }

    public double calculateTotalPrice(List<ProductVariantBundle> productVariantBundleList,
                                      List<ProductVariant> productVariantList) {
        double price = 0;
        int i = 0;
        for (ProductVariantBundle e : productVariantBundleList) {
            price += e.getQuantity() * productVariantList.get(i).getSalePrice();
            i++;
        }
        ;

        return price;
    }


    public void sort(List<PackagePrice> packagePriceList) {
        for (int a = 1; a < packagePriceList.size(); a++) {
            for (int b = 0; b < packagePriceList.size() - a; b++) {
                if (packagePriceList.get(b).getPiece() < packagePriceList.get(b + 1).getPiece()) {
                    // swap movies[b] with movies[b+1]
                    int piece = packagePriceList.get(b).getPiece();
                    double price = packagePriceList.get(b).getPrice();
                    packagePriceList.get(b).setPiece(packagePriceList.get(b + 1).getPiece());
                    packagePriceList.get(b).setPrice(packagePriceList.get(b + 1).getPrice());

                    packagePriceList.get(b + 1).setPiece(piece);
                    packagePriceList.get(b + 1).setPrice(price);
                }
            }
        }
    }
}
