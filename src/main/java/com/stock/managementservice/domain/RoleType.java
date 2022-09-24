package com.stock.managementservice.domain;

import lombok.Getter;

@Getter
public enum RoleType {
   USER("USER"),ADMIN("ADMIN");

   private String value;

   RoleType(String value){
      this.value=value;
   }
}
