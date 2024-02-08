package com.demo.cards;

import com.demo.cards.model.Card;

import java.util.List;

public class Driver {
    public static void main(String args[]){
        Deck deck = new Deck();
        printCards(deck.getCards());
        deck.shuffle();
        printCards(deck.getCards());
        List<List<Card>> lists = deck.distributeCards(3, 7);
        for(List<Card> cards : lists){
            printCards(cards);
        }
        System.out.println(deck.getCards().size());
        for(List<Card> cards : lists){
            deck.addCards(cards);
        }
        System.out.println(deck.getCards().size());
    }
    private static void printCards(List<Card> list){
        for(Card card : list){
            System.out.println(card.getSuite()+" "+card.getValue());
        }
        System.out.println();
    }
}
