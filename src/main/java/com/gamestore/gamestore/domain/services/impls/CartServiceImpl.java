package com.gamestore.gamestore.domain.services.impls;

import com.gamestore.gamestore.domain.dtos.BuyItemDto;
import com.gamestore.gamestore.domain.services.Cart;
import com.gamestore.gamestore.domain.services.CartService;
import com.gamestore.gamestore.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gamestore.gamestore.constants.ErrorMessages.*;
import static com.gamestore.gamestore.constants.GlobalConstants.*;

@Service
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final Cart cart;

    @Autowired
    public CartServiceImpl(UserService userService, Cart cart) {
        this.userService = userService;
        this.cart = cart;
    }

    @Override
    public String add(BuyItemDto buyItemDto) {

        String title = buyItemDto.getTitle();

        if (this.userService.isOwned(title)) {
            return String.format(ALREADY_BOUGHT, title);
        }

        if (this.cart.contains(title)) {
            return String.format(ALREADY_IN_CART, buyItemDto.getTitle());
        }

        this.cart.add(title, buyItemDto);

        return String.format(ADDED_TO_CART, buyItemDto.getTitle());
    }

    @Override
    public String remove(String title) {

        if (this.cart.isEmpty()) {
            return CART_EMPTY;
        }

        if (!this.cart.contains(title)) {
            return String.format(NOT_FOUND_IN_CART, title);
        }

        this.cart.remove(title);

        return String.format(REMOVED_FROM_CART, title);
    }

    @Override
    public String empty() {
        this.cart.empty();

        return NO_ITEMS_IN_CART;
    }

    @Override
    public boolean isCarEmpty() {
        return this.cart.isEmpty();
    }

    @Override
    public List<BuyItemDto> getCart() {
        return this.cart.getItems();
    }
}
