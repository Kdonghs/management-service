package com.stock.managementservice.controller;

import com.stock.managementservice.domain.Member;
import com.stock.managementservice.service.CustomOAuth2Service;
import com.stock.managementservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    final String REPLACE = "**********";

    @Autowired
    MemberService memberService;
    @Autowired
    private CustomOAuth2Service customOAuth2Service;

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model){
        Member member = customOAuth2Service.authenticationMember(authentication);
        member.setPassword(REPLACE);
        model.addAttribute("member",member);

        return  "profile/profile";
    }
    @GetMapping("/editProfile")
    public String editProfile(Authentication authentication, Model model){
        Member member = customOAuth2Service.authenticationMember(authentication);
        model.addAttribute("member",member);
        return  "profile/editProfile";
    }

    @PostMapping ("/editProfile.do")
    public String editProfileDo(@RequestParam(value = "newPass",defaultValue = "") String newPass,
                                Authentication authentication,Member member, Model model){

        Member flag = customOAuth2Service.authenticationMember(authentication);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(member.getPassword(), flag.getPassword())){
            flag.setPassword(REPLACE);
            model.addAttribute("member", flag);
            return  "profile/profile";
        }

        flag.setEmail(member.getEmail());
        flag.setPassword(encoder.encode(newPass));
        flag.setDescription(member.getDescription());

        memberService.memberSave(flag);
        flag.setPassword(REPLACE);

        model.addAttribute("member", flag);
        return  "profile/profile";
    }
}
