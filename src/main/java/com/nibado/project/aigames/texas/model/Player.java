package com.nibado.project.aigames.texas.model;

public class Player {
    private final CardSet hand = new CardSet();
    private final String name;
    private int stack;
    private boolean me = false;

    public Player(String name) {
        this.name = name;
    }

    public void setMe(boolean me) {
        this.me = me;
    }

    public boolean isMe() {
        return me;
    }

    public String getName() {
        return name;
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }

    public CardSet getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand.parse(hand);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", stack=" + stack +
                ", hand=" + hand +
                ", me=" + me +
                '}';
    }
}
