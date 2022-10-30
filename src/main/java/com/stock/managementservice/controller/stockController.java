package com.stock.managementservice.controller;

import com.stock.managementservice.domain.Member;
import com.stock.managementservice.service.CustomOAuth2Service;
import com.stock.managementservice.utill.JsonReader;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/stock")
public class stockController {
    @Autowired
    private CustomOAuth2Service customOAuth2Service;
    final JsonReader jsonReader = new JsonReader();

    @RequestMapping("/search")
    public String searchStock(@RequestParam String search, Authentication authentication, Model model) throws UnsupportedEncodingException {
        Member member = customOAuth2Service.authenticationMember(authentication);
        model.addAttribute("member",member);
        String code = "itmsNm=" + URLEncoder.encode(search, "UTF-8");

        JSONObject stock = jsonReader.stockObjectRead(code);
        model.addAttribute("stock",stock);

        return "stock/searchTable";
    }
    @RequestMapping("/search.do")
    public String searchStockRe(@RequestParam String search, Authentication authentication, Model model) throws UnsupportedEncodingException {
        Member member = customOAuth2Service.authenticationMember(authentication);
        model.addAttribute("member",member);
        String code = "itmsNm=" + URLEncoder.encode(search, "UTF-8");

        JSONObject stock = jsonReader.stockObjectRead(code);
        model.addAttribute("stock",stock);

        return "stock/searchTable";
    }

    @RequestMapping("/all")
    public String allStock(Authentication authentication, Model model){
        Member member = customOAuth2Service.authenticationMember(authentication);
        model.addAttribute("member",member);
        String code = "ref-data/symbols";

        JSONArray stock = jsonReader.stockArrayRead(code);
        model.addAttribute("stock",stock);

        return "stock/allTables";
    }
}
