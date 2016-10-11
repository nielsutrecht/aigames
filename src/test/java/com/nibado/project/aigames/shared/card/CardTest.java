package com.nibado.project.aigames.shared.card;

import org.junit.Test;

import static com.nibado.project.aigames.shared.card.Card.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @Test
    public void testToString() throws Exception {
        assertThat(valueOf(Card.Value.ACE, Card.Suit.D).toString()).isEqualTo("Ad");
        assertThat(valueOf(Card.Value.TWO, Card.Suit.H).toString()).isEqualTo("2h");
    }

    @Test
    public void testValueOf() throws Exception {
        System.out.println(Card.allCards());
    }

    @Test
    public void testAllCards() {
        assertThat(Card.allCards()).hasSize(13 * 4);
        assertThat(Card.allCards().get(0).value).isEqualTo(Card.Value.ACE);
        assertThat(Card.allCards().get(13 * 4 - 1).value).isEqualTo(Card.Value.TWO);
    }

}