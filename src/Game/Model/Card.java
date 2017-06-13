package Game.Model;


public abstract class Card {
    protected int cost;
    protected String name;
    protected Special special;
    protected String text;
    protected Books book;

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public Special getSpecial() {
        return special;
    }

    public String getText() {
        return text;
    }

    public void useSpecial(Player currentPlayer, Player opponent) {
        special.activate(this, currentPlayer, opponent);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
