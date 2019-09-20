package com.ecom.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@ToString
@Entity


public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "attribute is mandatory")
    @Size(min = 2,max = 20,message = "Attribute must be between 2 and 20 character long.")
    private String name;

    @NotBlank(message = "value is mandatory")
    @Size(min = 1,max = 20,message = "Value must be between 1 and 20 character long.")
    private String value;

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
