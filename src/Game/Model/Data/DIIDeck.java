package Game.Model.Data;


import Game.Model.*;
import Game.Model.Cards.HeroCard;
import Game.Model.Cards.SpecialCard;

public class DIIDeck implements DataProvider {

    @Override
    public Deck getDeck() {
        Deck deck = new Deck("Dziady II");

        Card starzec = new HeroCard(Books.DZIADY, "Starzec", 1, 2, null);
        Card duch = new HeroCard(Books.DZIADY, "Duch", 2, 3, (Card currentCard, Player currentPlayer, Player opponent)->{});
        Card rozia = new HeroCard(Books.DZIADY, "Rózia", 3, 2, null);
        Card widmoPana = new HeroCard(Books.DZIADY, "Widmo Pana", 3, 5, null);
        Card kruk = new HeroCard(Books.DZIADY, "Kruk", 3, 7, null);
        Card jozio = new HeroCard(Books.DZIADY, "Józio", 3, 7, null);
        Card widmoMlodzienca = new HeroCard(Books.DZIADY, "Widmo Młodzieńca", 3, 7, (Card currentCard, Player currentPlayer, Player opponent)->{});
        Card sowa = new HeroCard(Books.DZIADY, "Sowa", 3, 7, null);
        Card zosia = new HeroCard(Books.DZIADY, "Zosia", 3, 7, null);
        Card guslarz = new HeroCard(Books.DZIADY, "Guślarz", 3, 7, null);



        Card lament = new SpecialCard(Books.DZIADY, "Lament", 1, null);
        Card coToBedzie = new SpecialCard(Books.DZIADY, "Co to będzie?", 2, null);
        Card odkupienie = new SpecialCard(Books.DZIADY, "Odkupienie", 2, null);
        Card znakKrzyza = new SpecialCard(Books.DZIADY, "Znka krzyża", 3, null);
        Card slodycz = new SpecialCard(Books.DZIADY, "Słodycz", 4, (Card currentCard, Player currentPlayer, Player opponent)->{

        });
        Card gorycz = new SpecialCard(Books.DZIADY, "Gorycz", 6, null);
        Card aKysz = new SpecialCard(Books.DZIADY, "A Kysz!", 8, (Card currentCard, Player currentPlayer, Player opponent)->{

            int times = currentPlayer.getActiveCards().size();
            for (int i=0;i<times;i++){
                currentPlayer.getDeck().moveActiveCardTooCementary(currentPlayer.getActiveCards().get(0));
                System.out.print(i+ "");
            }

            for (int i=0;i<opponent.getActiveCards().size();i++){
                opponent.getDeck().moveActiveCardTooCementary(currentPlayer.getActiveCards().get(0));
            }
        });

        deck.add(starzec);
        deck.add(duch);
        deck.add(rozia);
        deck.add(widmoPana);
        deck.add(kruk);
        deck.add(jozio);
        deck.add(widmoMlodzienca);
        deck.add(sowa);
        deck.add(zosia);
        deck.add(guslarz);
        deck.add(lament);
        deck.add(coToBedzie);
        deck.add(odkupienie);
        deck.add(znakKrzyza);
        deck.add(slodycz);
        deck.add(gorycz);
        deck.add(aKysz);


        return deck;
    }
}
