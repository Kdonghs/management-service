package com.stock.managementservice.domain;

import lombok.Getter;

@Getter
public enum Social {
    LOCAL("LOCAL"), KAKAO("KAKAO"), GOOGLE("GOOGLE"), NAVER("NAVER");

    private String value;

    Social(String value) {
        this.value = value;
    }
}
