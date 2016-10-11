package com.nibado.project.aigames.texas.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CardSet {
    private Set<String> cards = new HashSet<>();

    public void parse(String cards) {
        this.cards.clear();
        this.cards.addAll(Arrays.asList(cards.substring(1, cards.length() - 1).split(",")));
    }

    @Override
    public String toString() {
        return "CardSet{" + cards + "}";
    }
}
