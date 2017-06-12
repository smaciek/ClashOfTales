package Game.Model.Cards;


import Game.Model.Books;
import Game.Model.Card;
import Game.Model.Special;
import com.sun.istack.internal.Nullable;

import java.awt.print.Book;

public class HeroCard extends Card {
    private final int basicStrength;
    private int strength;
    private Special special;


    public HeroCard(Books book, String name, int cost, int strength, @Nullable Special special) {
        this.book = book;
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

    private void getDamage(int damage){
        strength-=damage;
    }


}
