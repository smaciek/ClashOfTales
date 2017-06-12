package Game.Model.Cards;

import Game.Model.Books;
import Game.Model.Card;
import Game.Model.Special;
import com.sun.istack.internal.NotNull;

import java.awt.print.Book;

public class SpecialCard extends Card {

    public SpecialCard(Books book, String name, int cost, @NotNull Special special) {
        this.book = book;
        this.name = name;
        this.cost = cost;
        this.special = special;
    }

    public void playCard(){

    }

}
