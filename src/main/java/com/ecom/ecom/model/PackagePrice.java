package com.ecom.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@ToString
@Entity


public class PackagePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Piece should not be blank.")
    @Min(value = 2,message = "Number of piece should be at least 2")
    private int piece;

    @NotNull(message = "Price should not be blank")
    @Min(value = 0,message = "Price should not be negative")
    private double price;

    public PackagePrice(int piece, double price) {
        this.piece = piece;
        this.price = price;
    }

}
