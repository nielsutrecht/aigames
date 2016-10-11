package com.nibado.project.aigames.shared.card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.toList;

public class Card implements Comparable<Card> {
    private static final Map<String, Card> CARD_MAP;
    private static final List<Card> CARD_LIST;
    public final Value value;
    public final Suit suit;
    private final String s;

    private Card(final Value value, final Suit suit) {
        this.value = value;
        this.suit = suit;
        this.s = value.c + "" +suit.c;
    }

    @Override
    public String toString() {
        return s;
    }

    @Override
    public int compareTo(Card o) {
        if(o == this) {
            return 0;
        }
        else if(o.value.ordinal() == this.value.ordinal()) {
            return Integer.compare(o.suit.ordinal(), this.suit.ordinal());
        }
        else {
            return Integer.compare(o.value.ordinal(), this.value.ordinal());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (value != card.value) return false;
        return suit == card.suit;
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }

    public static Card valueOf(Value value, Suit suit) {
        return valueOf(value.c + "" +suit.c);
    }

    public static Card valueOf(final String s) {
        return CARD_MAP.get(s);
    }

    public static List<Card> allCards() {
        return CARD_LIST;
    }

    public enum Value {
        TWO('2'),
        THREE('3'),
        FOUR('4'),
        FIVE('5'),
        SIX('6'),
        SEVEN('7'),
        EIGHT('8'),
        NINE('9'),
        TEN('T'),
        JACK('J'),
        QUEEN('Q'),
        KING('K'),
        ACE('A');

        private final char c;

        Value(final char c) {
            this.c = c;
        }
    }

    public enum Suit {
        D, C, H, S;

        private final char c;

        Suit() {
            c = Character.toLowerCase(toString().charAt(0));
        }
    }

    static {
        Map<String, Card> map = new HashMap<>(Value.values().length * Suit.values().length);

        for(int s = 0;s < Suit.values().length;s++) {
            for(int v = 0; v < Value.values().length;v++) {
                Card c = new Card(Value.values()[v], Suit.values()[s]);
                map.put(c.toString(), c);
            }
        }

        CARD_MAP = unmodifiableMap(map);
        CARD_LIST = unmodifiableList(map.values().stream().sorted().collect(toList()));
    }
}
