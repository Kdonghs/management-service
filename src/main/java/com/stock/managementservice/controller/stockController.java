package com.stock.managementservice.controller;

import com.google.gson.Gson;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/stock")
public class stockController {
    @Autowired
    private CustomOAuth2Service customOAuth2Service;
    final JsonReader jsonReader = new JsonReader();

    /*@RequestMapping("/search")
    public void searchStock(@RequestParam String keyword,
                              @RequestParam String state,HttpServletResponse response) throws IOException {

        JSONArray stock=null;
        System.out.println(state);

        if (state.equals("us")){
            System.out.println(keyword);
            stock = jsonReader.usStockObjectRead(keyword);
//            model.addAttribute("stock",stock);
        }

        if (state.equals("kor")){
            String code = "likeItmsNm=" + URLEncoder.encode(keyword, "UTF-8");
            stock = jsonReader.korStockObjectRead(code);
//            model.addAttribute("stock",stock);
        }

        response.getWriter().print(stock);
        *//*return "stock/searchTable";*//*
    }*/
    /*@RequestMapping("/search.do")
    public String searchStockRe(@RequestParam String search, Authentication authentication, Model model) throws UnsupportedEncodingException {
        Member member = customOAuth2Service.authenticationMember(authentication);
        model.addAttribute("member",member);

        String code = "itmsNm=" + URLEncoder.encode(search, "UTF-8");
        JSONObject stock = jsonReader.korStockObjectRead(code);
        model.addAttribute("stock",stock);

        return "stock/searchTable";
    }*/

    /*@RequestMapping("/all")
    public String allStock(Authentication authentication, Model model){
        Member member = customOAuth2Service.authenticationMember(authentication);
        model.addAttribute("member",member);
        String code = "ref-data/symbols";

        JSONArray stock = jsonReader.stockArrayRead(code);
        model.addAttribute("stock",stock);

        return "stock/allTables";
    }*/
}
