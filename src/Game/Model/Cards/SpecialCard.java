package Game.Model.Cards;

import Game.Model.Books;
import Game.Model.Card;
import Game.Model.Special;


public class SpecialCard extends Card {

    public SpecialCard(Books book, String name, int cost, Special special) {
        this.book = book;
        this.text = "";
        this.name = name;
        this.cost = cost;
        this.special = special;
    }

    public SpecialCard(Books book, String text, String name, int cost, Special special) {
        this.book = book;
        this.text = text;
        this.name = name;
        this.cost = cost;
        this.special = special;

    }


}
