package com.stock.managementservice.service;

import com.stock.managementservice.domain.Member;
import com.stock.managementservice.domain.RoleType;
import com.stock.managementservice.domain.SNSInfo;
import com.stock.managementservice.domain.Social;
import com.stock.managementservice.dto.OAuthAttributes;
import com.stock.managementservice.dto.SessionMember;
import com.stock.managementservice.repository.MemberRepository;
import com.stock.managementservice.repository.snsInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final snsInfoRepository snsInfoRepository;
    private final HttpSession httpSession;
    private final MemberService memberService;

    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(request);

        String registrationId = request.getClientRegistration().getRegistrationId();
        String userNameAttributeName = request.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Member member = saveOrUpdate(attributes, registrationId);
        httpSession.setAttribute("snsInfo", new SessionMember(member));

        if (registrationId.equals("kakao")){
            return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getRoleType().getValue())),
                    oAuth2User.getAttributes(), attributes.getNameAttributeKey());
        }

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getRoleType().getValue())),
                attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private Member saveOrUpdate(OAuthAttributes attributes, String registrationId) {
        Optional<Member> flag;
        if (!snsInfoRepository.findSNSInfoBySnsID(attributes.getSub()).isEmpty()) {
            Optional<SNSInfo> snsInfoBySnsID = snsInfoRepository.findSNSInfoBySnsID(attributes.getSub());
            flag = memberRepository.findById(snsInfoBySnsID.get().getMember().getId());
            return flag.get();
        }
        flag = memberRepository.findMemberByEmail(attributes.getEmail());
        SNSInfo snsInfo = new SNSInfo(attributes.getSub(), attributes.getName(), attributes.getPicture());

        System.out.println(attributes.getSub());
        System.out.println(registrationId);

        if (registrationId.equals("google")) {
            snsInfo.setSnsType(Social.GOOGLE);
        }else if (registrationId.equals("naver")){
            snsInfo.setSnsType(Social.NAVER);
        }else if (registrationId.equals("kakao")){
            snsInfo.setSnsType(Social.KAKAO);
        }
        Member member;

        if (flag.isEmpty()) {
            member = new Member();
            member.setEmail(attributes.getEmail());
            member.setName(attributes.getName());
            member.setRoleType(RoleType.USER);
            member.getSnsInfoList().add(snsInfo);
            memberRepository.save(member);

            snsInfo.setMember(memberRepository.findMemberByEmail(attributes.getEmail()).get());
            snsInfoRepository.save(snsInfo);

            return member;
        } else {
            member = flag.get();
            snsInfo.setMember(member);
            snsInfoRepository.save(snsInfo);
        }

        return memberRepository.save(member);
    }

    public Optional<SNSInfo> snsInfo(String sub) {
        return snsInfoRepository.findSNSInfoBySnsID(sub);
    }

    public Optional<SNSInfo> memberid(Member memberid) {
        return snsInfoRepository.findSNSInfoByMemberId(memberid);
    }

    public void snsInfoSave(SNSInfo snsInfo) {
        snsInfoRepository.save(snsInfo);
    }

    //local login or oauth2 login check
    public Member authenticationMember(Authentication authentication) {
        Member member;
        if (memberService.member(authentication.getName()).isEmpty()) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            /*Map<String, Object> attributes = token.getPrincipal().getAttributes();*/
            Optional<SNSInfo> flag = snsInfo(token.getName());

            member = memberService.member(flag.get().getMember().getId()).get();
        } else {
            member = memberService.member(authentication.getName()).get();
        }
        return member;
    }

}
