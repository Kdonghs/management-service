package com.stock.managementservice.controller;

import com.stock.managementservice.domain.Member;
import com.stock.managementservice.service.CustomOAuth2Service;
import com.stock.managementservice.service.MyStarsService;
import com.stock.managementservice.service.MyStockService;
import com.stock.managementservice.utill.JsonReader;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor
public class stockController {
    @Autowired
    private CustomOAuth2Service customOAuth2Service;
    @Autowired
    private MyStarsService myStars;
    @Autowired
    private MyStockService myStock;

    final JsonReader jsonReader = new JsonReader();

    @RequestMapping("/search")
    public String searchStock(@RequestParam String search,@RequestParam String state,
                              Authentication authentication, Model model) throws UnsupportedEncodingException {
        Member member = customOAuth2Service.authenticationMember(authentication);
        model.addAttribute("member",member);

        if (state.equals("us")){
//            JSONArray json = jsonReader.usStockObjectRead(search);
            model.addAttribute("ticker", search);
        }else if (state.equals("kor")){
            JSONObject json = jsonReader.korStockObjectRead(URLEncoder.encode(search, "UTF-8"),1,1);
            model.addAttribute("ticker", json);
            model.addAttribute("checkStar",myStars.checkStar((String) json.get("srtnCd"),member));
        }

        return "stock/searchTable";
       /* return "redirect:/";*/
    }
    @RequestMapping("/stars")
    @ResponseBody
    public ResponseEntity myStock(@RequestParam String ticker,Authentication authentication){
        Member member = customOAuth2Service.authenticationMember(authentication);
        System.out.println(myStars.checkStar(ticker,member));
        if (myStars.checkStar(ticker,member)){
            myStars.removeStar(ticker,member);
            return ResponseEntity.ok("remove");

        }else {
            myStars.addStar(ticker,member);
            return ResponseEntity.ok("add");
        }

    }
   /* @RequestMapping("/search.do")
    public String searchStockRe(@RequestParam String search,@RequestParam String state,
                                Authentication authentication, Model model) throws UnsupportedEncodingException {
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

//        JSONArray stock = jsonReader.stockArrayRead(code);
        model.addAttribute("stock",stock);

        return "stock/allTables";
    }*/
}
