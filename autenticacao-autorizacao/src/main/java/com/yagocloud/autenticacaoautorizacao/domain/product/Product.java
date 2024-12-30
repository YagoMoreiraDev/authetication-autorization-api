package com.yagocloud.autenticacaoautorizacao.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "product")
@Table(name = "product")
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Integer price;

    public Product() {
    }

    public Product(String id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(ProductRequestDTO productRequestDTO) {
        this.name = productRequestDTO.name();
        this.price = productRequestDTO.price();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
