package com.stock.managementservice.controller;

import com.stock.managementservice.domain.Member;
import com.stock.managementservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/memberList")
public class memberListController {

    @Autowired
    MemberService memberService;

    @GetMapping("/memberList")
    public String memberList(Model model){

        List<Member> members = memberService.memberAll();
        model.addAttribute("members",members);
        return "memberList/memberList";
    }

    @PostMapping("/searchMember")
    public String searchMember(@RequestParam(value = "search", defaultValue = "") String search,
                             @RequestParam(value = "status", defaultValue = "") String status,Model model){

        List<Member> members;
        if (status.equals("name")){
            members = memberService.searchMemberName(search);
        }else if (status.equals("age")){
            members = memberService.searchMemberAge(Integer.parseInt(search));
        }else if (status.equals("emil")){
            members = memberService.searchMemberEmail(search);
        }else {
            members = new ArrayList<>();
        }

        model.addAttribute("members",members);
        return "memberList/memberList";
    }
}
