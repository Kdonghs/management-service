package com.stock.managementservice.service;

import com.stock.managementservice.domain.Item;
import com.stock.managementservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public boolean createItem(Item item) {
        try {
            itemRepository.save(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Item> itemAll() {
        return itemRepository.findAll();
    }

    public List<Item> searchItemName(String search) {
        return itemRepository.findItemByNameContaining(search);
    }

    public List<Item> searchItemPrice(int search) {
        return itemRepository.findItemByPriceLike(search);
    }

    public Optional<Item> item(Long itemID) {
        return itemRepository.findById(itemID);
    }

    public void itemSave(Item item){
        itemRepository.save(item);
    }

    public void deleteItem(Item item){
        itemRepository.delete(item);
    }
}
