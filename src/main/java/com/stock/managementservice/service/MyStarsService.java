package com.stock.managementservice.service;

import com.stock.managementservice.domain.Member;
import com.stock.managementservice.domain.MyStars;
import com.stock.managementservice.repository.MyStarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyStarsService {
    @Autowired
    private MyStarsRepository myStarsRepository;

    public Boolean checkStar(String ticker, Member member){
        System.out.println(ticker);
        System.out.println(member.getId());
        return myStarsRepository.existsMyStarsByTickerAndMember_Id(ticker,member.getId());
    }

    public Boolean addStar(String ticker,String name, Member member){
        MyStars myStars = new MyStars(ticker,name,member);
        try {
            myStarsRepository.save(myStars);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean removeStar(String ticker, Member member){
        Optional<MyStars> myStars = myStarsRepository.findMyStarsByTickerAndMember_Id(ticker,member.getId());
        try {
            myStarsRepository.delete(myStars.get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<MyStars> getAll(){
        return myStarsRepository.findAll();
    }
}
