package com.stock.managementservice.domain;

import com.stock.managementservice.entity.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@Table
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MyStock extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String ticker;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private market market;

    @NotNull
    private int price;


    private int many;

    private int exchange;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public MyStock(String ticker, String name, market market, int price, int many){
        this.ticker = ticker;
        this.name = name;
        this.market = market;
        this.price = price;
        this.many = many;
    }

    @Builder
    public MyStock(String ticker, String name, market market, int price, int many, int exchange){
        this.ticker = ticker;
        this.name = name;
        this.market = market;
        this.price = price;
        this.many = many;
        this.exchange = exchange;
    }

    public MyStock update(int price, int many){
        this.price = price;
        this.many = many;

        return this;
    }

    public MyStock update(int price, int many, int exchange){
        this.price = price;
        this.many = many;
        this.exchange = exchange;

        return this;
    }

}
