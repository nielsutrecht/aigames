package com.nibado.project.aigames.texas.model;

import com.nibado.project.aigames.shared.card.Card;

import java.util.HashSet;
import java.util.Set;

public class CardSet {
    private Set<Card> cards = new HashSet<>();

    public void parse(String cards) {
        this.cards.clear();
        for(String s : cards.substring(1, cards.length() - 1).split(",")) {
            this.cards.add(Card.valueOf(s));
        }
    }

    @Override
    public String toString() {
        return "CardSet{" + cards + "}";
    }
}
