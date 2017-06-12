package Game.Model;

import Game.Model.Cards.HeroCard;

import java.util.List;
import java.util.Random;

public class Player {
    private static final int START_LIFE = 30;
    private static final int START_HAND_CARD_NUMBER = 1;

    private int life;
    private int mana = 0;
    private int manaBonus = 0;
    private String name;
    private Deck deck;
//    private List<Card> hand;
//    private List<HeroCard> activeCards;
//    private List<Card> cementary;

    private Random random = new Random();

    public Player(String name, Deck deck) {
        this.life = START_LIFE;
        this.name = name;
        this.deck = deck;
//        this.hand = new ArrayList<>();
//        this.activeCards = new ArrayList<>();
//        this.cementary = new ArrayList<>();
        addToHand(START_HAND_CARD_NUMBER);
    }

    public void startRound(int roundNumber) {
        mana = roundNumber * 2 - 1;
        mana += manaBonus;
        manaBonus = 0;
    }

    public void getDamage(int damage) {
        life -= damage;
    }

    public void playCard(Card card, Player opponent) {
        if(card.cost<mana) {
            mana-=card.cost;
            deck.getHand().remove(card);
            if (card instanceof HeroCard) deck.getActiveHeroes().add((HeroCard) card);
            // else card.useSpecial(this, opponent);
            if (card.getSpecial() != null) card.getSpecial().activate(card, this, opponent);
        }
    }

    public void setManaBonus(int bonus) {
        manaBonus = bonus;
    }

    private void addToHand(int n) {
        for (int i = 0; i < n; i++) {
            Card card = deck.getDeck().remove(random.nextInt(deck.getDeck().size()));
//            Card card = deck.getDeck().remove(0);
            deck.getHand().add(card);
        }
    }

    public String getName() {
        return name;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<HeroCard> getActiveCards() {
        return deck.getActiveHeroes();
    }

    public List<Card> getCementary() {
        return deck.getCementary();
    }

    public List<Card> getHand() {
        return deck.getHand();
    }

    public int getLife() {
        return life;
    }

    public int getMana() {
        return mana;
    }
}