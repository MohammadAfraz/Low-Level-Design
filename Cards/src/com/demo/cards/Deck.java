package com.demo.cards;

import com.demo.cards.exceptions.DistributionNotPossibleException;
import com.demo.cards.model.Card;
import com.demo.cards.model.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    List<Card> cards;
    Random random;
    Integer MAX_CARDS = 52;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        random = new Random();
    }
    private void initializeDeck(){
        for(Suit suit : Suit.values()){
            for(int i=1; i<14; i++) {
                cards.add(new Card(suit, i));
            }
        }
    }

    public List<Card> shuffle(){
        int swapIndex;
        for(int i = MAX_CARDS-1; i > 0; i--){
            swapIndex = random.nextInt(i+1);
            Card lastCardToSwap = cards.get(i);
            cards.set(i, cards.get(swapIndex));
            cards.set(swapIndex, lastCardToSwap);
        }
        return this.cards;
    }

    public List<Card> getCards(){
        return cards;
    }

    public List<List<Card>> distributeCards(int people, int cardsPerPerson){
        validate(people, cardsPerPerson);
        shuffle();
        List<List<Card>> distribution = new ArrayList<>();
        for(int i=0; i<people; i++){
            List<Card> list = new ArrayList<>();
            for(int j=0; j < cardsPerPerson; j++) {
                list.add(cards.remove(cards.size()-1));
            }
            distribution.add(list);
        }
        return distribution;
    }

    private void validate(int people, int cardsPerPerson){
        if(people * cardsPerPerson > MAX_CARDS){
            throw new DistributionNotPossibleException("Cards required for distribution exceeds Max available Cards");
        }
    }

    public void addCards(List<Card> list){
        cards.addAll(list);
    }

}
