package com.stock.managementservice.controller;

import com.stock.managementservice.domain.Member;
import com.stock.managementservice.service.CustomOAuth2Service;
import com.stock.managementservice.utill.JsonReader;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/stock")
public class stockController {
    @Autowired
    private CustomOAuth2Service customOAuth2Service;
    final JsonReader jsonReader = new JsonReader();

    @RequestMapping("/search")
    public String searchStock(@RequestParam String search, Authentication authentication, Model model){
        Member member = customOAuth2Service.authenticationMember(authentication);
        model.addAttribute("member",member);

        JSONObject stock = jsonReader.stockRead(search);
        model.addAttribute("stock",stock.get("quote"));

        return "stock/tables";
    }
}
