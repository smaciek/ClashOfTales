package Game.Model;


import Game.Controller.GameController;
import Game.Model.Cards.HeroCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> cementary;
    private List<HeroCard> activeHeroes;

    private List<HeroCard> items;
    private String name;

    private Random random;

    public Deck(String name) {
        this.name = name;
        this.deck = new ArrayList<>();
        this.items = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.cementary = new ArrayList<>();
        this.activeHeroes = new ArrayList<>();
        this.random = new Random();
    }

    public Deck(String name, List<Card> deck) {
        this.name = name;
        this.deck = deck;
        this.items = new ArrayList<>();
    }

    public void add(Card card){
        deck.add(card);
    }

    public void add(List<Card> cards){
        deck.addAll(cards);
    }

    public void addItem(HeroCard card){
        items.add(card);
    }

    public void playItem(HeroCard item){
        activeHeroes.add(item);
    }

    public Card drawCard(){
        Card card = null;
        if(deck.size()>0 && hand.size()< GameController.MAX_HAND) {
            card = deck.remove(random.nextInt(deck.size()));
            hand.add(card);
        }

        return card;
    }
    public void moveActiveCardTooCementary(HeroCard card){
        int index = activeHeroes.indexOf(card);
        cementary.add(card);
        activeHeroes.remove(card);
    }

    public void moveToCementary(Card card){
        moveTo(card, cementary);
    }

    public void moveCard(Card card, List<Card> source, List<Card> destination){
        int index = source.indexOf(card);
        destination.add(source.remove(index));
    }



    public void moveTo(Card card, List<Card> destination){
        destination.add(card);
    }

    public List<Card> getDeck() {
        return deck;
    }

    public List<HeroCard> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getCementary() {
        return cementary;
    }

    public List<HeroCard> getActiveHeroes() {
        return activeHeroes;
    }

    private String buildString(){
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(":\n");
        builder.append(deck);
        builder.append("\nHand:\n");
        builder.append(hand);
        builder.append("\nActiveHeroes:\n");
        builder.append(activeHeroes);
        builder.append("\nCementary:\n");
        builder.append(cementary);
        return builder.toString();
    }

    @Override
    public String toString() {
        return buildString();
    }
}
