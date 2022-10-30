package com.stock.managementservice.service;

import com.stock.managementservice.domain.Member;
import com.stock.managementservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Optional<Member> member(Long memberKey) {
        return memberRepository.findById(memberKey);
    }
    public Optional<Member> member(String username) {
        return memberRepository.findMemberByUsername(username);
    }
    public Optional<Member> emailMember(String email) {
        return memberRepository.findMemberByEmail(email);
    }

    public void memberSave(Member member){
        memberRepository.save(member);
    }

    public boolean createMember(Member member){
        try {
            memberRepository.save(member);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    /*public Optional<Member> loginOk(String username, String pw,String role){
        RoleType roleTypeByEnum = RoleType.valueOf(role);
        Optional<Member> flag = memberRepository.findMemberByRoleTypeAndUsernameAndPassword(roleTypeByEnum,username,pw);

        return flag;

    }*/

    public Optional<Member> searchId(String name, String email){
        return memberRepository.findMemberByNameAndEmail(name,email);
    }

    public Optional<Member> searchPw(String name, String email, String id){
        return memberRepository.findMemberByNameAndEmailAndId(name,email,id);
    }

    public List<Member> memberAll(){
        return memberRepository.findAll();
    }

    public List<Member> searchMemberAge(int search){
        return memberRepository.findMemberByAgeLike(search);
    }
    public List<Member> searchMemberName(String search){
        return memberRepository.findMemberByNameContaining(search);
    }
    public List<Member> searchMemberEmail(String search){
        return memberRepository.findMemberByEmailContaining(search);
    }

    @Transactional
    public boolean existsByMemberUsername(String username){

        return memberRepository.existsByUsername(username);
    }

    @Transactional
    public boolean existsByMemberEmail(String email){

        return memberRepository.existsByEmail(email);
    }

}
