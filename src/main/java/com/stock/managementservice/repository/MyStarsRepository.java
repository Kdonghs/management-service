package com.stock.managementservice.repository;

import com.stock.managementservice.domain.MyStars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyStarsRepository extends JpaRepository<MyStars,Long> {
    Optional<MyStars> findMyStarsByTickerAndMember_Id(String ticker, Long id);
    Boolean existsMyStarsByTickerAndMember_Id(String ticker, Long id);
}
