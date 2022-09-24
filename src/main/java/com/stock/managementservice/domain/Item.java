package com.stock.managementservice.domain;

import com.stock.managementservice.entity.BaseTimeEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "item")
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer volume;

    @Lob
    private String description;


    public Item(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Item(){}
}
