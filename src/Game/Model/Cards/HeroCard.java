package Game.Model.Cards;


import Game.Model.Books;
import Game.Model.Card;
import Game.Model.Special;

public class HeroCard extends Card {
    private final int basicStrength;
    private int strength;
    private Special special;
    private boolean tapped = true;


    public HeroCard(Books book, String name, int cost, int strength,Special special) {
        this.book = book;
        this.name = name;
        this.cost = cost;
        this.basicStrength = strength;
        this.strength = strength;
        this.special = special;
    }

    public HeroCard(Books book,String text, String name, int cost, int strength,Special special) {
        this.book = book;
        this.text = text;
        this.name = name;
        this.cost = cost;
        this.basicStrength = strength;
        this.strength = strength;
        this.special = special;
    }


    public int getStrength() {
        return strength;
    }


    public boolean hit(int damage){
        changeStrengthBy(-damage);
        return strength>0;
    }

    public void changeStrengthBy(int change){
        strength+=change;
    }


    public boolean isTapped() {
        return tapped;
    }

    public void setTapped(boolean tapped) {
        this.tapped = tapped;
    }
}
