package com.stock.managementservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@Table
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MyStars {
    private String ticker;

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

   public MyStars(String ticker,String name, Member member){
       this.ticker = ticker;
       this.name = name;
       this.member = member;
   }
}
