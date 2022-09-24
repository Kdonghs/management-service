package com.stock.managementservice.repository;

import com.stock.managementservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findItemByNameContaining(String search);
    List<Item> findItemByPriceLike(int search);

    @Override
    Optional<Item> findById(Long aLong);
}
