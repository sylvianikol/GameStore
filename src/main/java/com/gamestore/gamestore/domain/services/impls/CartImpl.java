package com.gamestore.gamestore.domain.services.impls;

import com.gamestore.gamestore.domain.dtos.BuyItemDto;
import com.gamestore.gamestore.domain.services.Cart;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CartImpl implements Cart {

    private Map<String, BuyItemDto> items;

    public CartImpl() {
        this.items = new HashMap<>();
    }

    @Override
    public List<BuyItemDto> getItems() {
        return new ArrayList<>(items.values());
    }

    public void setItems(Map<String, BuyItemDto> items) {
        this.items = items;
    }

    @Override
    public boolean contains(String title) {
        return this.items.containsKey(title);
    }

    @Override
    public void add(String title, BuyItemDto buyItemDto) {
        this.items.put(title, buyItemDto);
    }

    @Override
    public void remove(String title) {
        this.items.remove(title);
    }

    @Override
    public void empty() {
        this.items.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.items.isEmpty();
    }
}
