package com.demo.cards.model;

public class Card {
    Suit suit;
    int value;
    public Card(Suit suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuite() {
        return suit;
    }

    public void setSuite(Suit suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
