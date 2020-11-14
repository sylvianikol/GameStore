package com.gamestore.gamestore.domain.entities;

import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@AccessType(value = AccessType.Type.PROPERTY)
@Table(name = "orders")
public class Order extends BaseEntity {

    private User buyer;

    private Set<Game> orderedGames;

    public Order() {
    }

    @ManyToOne(cascade = { ALL})
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToMany(cascade = { PERSIST, MERGE }, fetch = EAGER)
    @JoinTable(name = "orders_ordered_games",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    public Set<Game> getOrderedGames() {
        return orderedGames;
    }

    public void setOrderedGames(Set<Game> orderedGames) {
        this.orderedGames = orderedGames;
    }
}
