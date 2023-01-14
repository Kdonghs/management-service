package com.stock.managementservice.domain;

import com.stock.managementservice.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String username;

    private String password;

    private String name;

    @Column(length = 200)
    private Integer age;

    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Lob
    private String description;

    @OneToMany(mappedBy = "member")
    private List<SNSInfo> snsInfoList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MyStock> myStocks = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MyStars> myStars = new ArrayList<>();

    @Builder
    public Member(String username, String password, String name, Integer age, String email, RoleType roleType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
        this.roleType = roleType;
    }

    public Member update(String password,Integer age, String username) {
        this.username = username;
        this.password = password;
        this.age = age;

        return this;
}

}
