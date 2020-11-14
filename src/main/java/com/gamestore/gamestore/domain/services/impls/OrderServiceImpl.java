package com.gamestore.gamestore.domain.services.impls;

import com.gamestore.gamestore.domain.dtos.BuyItemDto;
import com.gamestore.gamestore.domain.entities.Game;
import com.gamestore.gamestore.domain.entities.Order;
import com.gamestore.gamestore.domain.entities.User;
import com.gamestore.gamestore.domain.repository.OrderRepository;
import com.gamestore.gamestore.domain.services.CartService;
import com.gamestore.gamestore.domain.services.GameService;
import com.gamestore.gamestore.domain.services.OrderService;
import com.gamestore.gamestore.domain.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gamestore.gamestore.constants.ErrorMessages.ALREADY_BOUGHT;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final GameService gameService;
    private final UserService userService;
    private final CartService cartService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            GameService gameService,
                            UserService userService,
                            CartService cartService,
                            ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.gameService = gameService;
        this.userService = userService;
        this.cartService = cartService;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<String> placeOrder() {

        Order order = new Order();
        order.setOrderedGames(new HashSet<>());

        for (BuyItemDto item : this.cartService.getCart()) {
            String gameTitle = item.getTitle();

            if (this.userService.isOwned(gameTitle)) {
                System.out.printf((ALREADY_BOUGHT) + "%n", gameTitle);
                continue;
            }

            Game game = this.userService.addToOwnedGames(item.getId());

            order.getOrderedGames().add(game);
        }

        User user = this.userService.getUser();
        order.setBuyer(user);

        this.orderRepository.saveAndFlush(order);

        return order.getOrderedGames()
                .stream()
                .map(Game::getTitle)
                .collect(Collectors.toList());

    }
}
