package com.stock.managementservice.controller;

import com.stock.managementservice.domain.Member;
import com.stock.managementservice.service.CustomOAuth2Service;
import com.stock.managementservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private CustomOAuth2Service customOAuth2Service;

    /*@GetMapping("/") //spring security x
    public String home(@SessionAttribute(name = sessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){

        if (loginMember == null){
            return "login/loginForm";
        }

        // 세션에 회원 데이터가 있으면 로그인한 유저를 위한 홈 화면으로 이동
        model.addAttribute("member", loginMember);
        return "home";
    }*/

    @GetMapping("/")
    public String home(Authentication authentication, Model model){
        if (authentication==null){
            return "login/loginForm";
        }

        /*Map<String, Object> attributes = token.getPrincipal().getAttributes();*/
        Member member = customOAuth2Service.authenticationMember(authentication);
        /*
        if (memberService.member(authentication.getName()).isEmpty()){
            Optional<SNSInfo> flag = customOAuth2Service.snsInfo((String) attributes.get("sub"));
            member=memberService.member(flag.get().getMemberId().getId()).get();
        }else {
            member = memberService.member(authentication.getName()).get();
        }*/

        model.addAttribute(member);
        System.out.println(authentication);
        return "home";
    }


    @GetMapping("login/loginForm")
    public String loginForm(){
        return "login/loginForm";
    }
    @GetMapping("login/createAccount")
    public String createAccount(){return "login/createAccount";}
    @GetMapping("login/searchID")
    public String searchID(){
        return "login/searchID";
    }
    @GetMapping("login/searchPW")
    public String searchPW(){
        return "login/searchPW";
    }
    @GetMapping("/sidebar/404")
    public String notfound(){return "/sidebar/404";}
    @GetMapping("/item/createItem")
    public String createItem(){
        return "item/createItem";
    }

}
