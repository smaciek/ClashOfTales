package Game.Model.Data;


import Game.Model.*;
import Game.Model.Cards.HeroCard;
import Game.Model.Cards.SpecialCard;

import java.util.Random;

/**
 * Klasa generująca talię "Dziadów cz. II"
 */

public class DIIDeck implements DataProvider {
    private Random random = new Random();

    @Override
    public Deck getDeck() {
        Deck deck = new Deck("Dziady II");

        Special zwawosc = (Card currentCard, Player currentPlayer, Player opponent) -> {
            if (currentCard instanceof HeroCard) ((HeroCard) currentCard).setTapped(false);
        };

        Card starzec = new HeroCard(Books.DZIADY, "Starzec", 1, 2, (Card currentCard, Player currentPlayer, Player opponent) -> {
        });
        Card duch = new HeroCard(Books.DZIADY, "Duch", 2, 3, (Card currentCard, Player currentPlayer, Player opponent) -> {
        });
        Card rozia = new HeroCard(Books.DZIADY, "Rózia", 3, 2, null);
        Card widmoPana = new HeroCard(Books.DZIADY, "Widmo Pana", 3, 5, zwawosc);
        Card kruk = new HeroCard(Books.DZIADY, "Kruk", 3, 7, (Card currentCard, Player currentPlayer, Player opponent) -> {
        });
        Card jozio = new HeroCard(Books.DZIADY, "Józio", 4, 4, null);
        Card widmoMlodzienca = new HeroCard(Books.DZIADY, "Widmo Młodzieńca", 4, 5, (Card currentCard, Player currentPlayer, Player opponent) -> {
        });
        Card sowa = new HeroCard(Books.DZIADY, "Sowa", 5, 5, zwawosc);
        Card zosia = new HeroCard(Books.DZIADY, "Zosia", 6, 4, null);
        Card guslarz = new HeroCard(Books.DZIADY, "Guślarz", 7, 5, null);


        Card lament = new SpecialCard(Books.DZIADY, "Gracz otrzymuje bonus 2 many w kolejnej rundzie", "Lament", 1, (Card currentCard, Player currentPlayer, Player opponent) -> {
            currentPlayer.setManaBonus(2);
        });
        Card coToBedzie = new SpecialCard(Books.DZIADY, "Zwiększa siłę losowago stronnika o liczbę wszystkich stronników na polu bitwy", "Co to będzie?", 2, (Card currentCard, Player currentPlayer, Player opponent) -> {
            int i = currentPlayer.getActiveCards().size() + opponent.getActiveCards().size();

            if (random.nextBoolean()) {
                currentPlayer.getActiveCards().get(random.nextInt(currentPlayer.getActiveCards().size())).changeStrengthBy(i);
            } else {
                opponent.getActiveCards().get(random.nextInt(opponent.getActiveCards().size())).changeStrengthBy(i);
            }
        });
        Card odkupienie = new SpecialCard(Books.DZIADY, "Niszczy losowego strnnika gracza i przeciwnika", "Odkupienie", 2, (Card currentCard, Player currentPlayer, Player opponent) -> {
            if (currentPlayer.getActiveCards().size() > 0 && opponent.getActiveCards().size() > 0) {
                HeroCard card = currentPlayer.getActiveCards().get(random.nextInt(currentPlayer.getActiveCards().size()));
                currentPlayer.getDeck().moveActiveCardTooCementary(card);

                HeroCard opCard = opponent.getActiveCards().get(random.nextInt(opponent.getActiveCards().size()));
                opponent.getDeck().moveActiveCardTooCementary(opCard);

            }
        });

        Card znakKrzyza = new SpecialCard(Books.DZIADY, "Dobierasz 2 karty", "Znka krzyża", 3, (Card currentCard, Player currentPlayer, Player opponent) -> {
            if (currentPlayer.getDeck().getHand().size() < 5) currentPlayer.getDeck().drawCard();
            if (currentPlayer.getDeck().getHand().size() < 5) currentPlayer.getDeck().drawCard();
        });

        Card slodycz = new SpecialCard(Books.DZIADY, "Gracz lub Losowy stronnik otrzymuje 6 życia/siły", "Słodycz", 4, (Card currentCard, Player currentPlayer, Player opponent) -> {
            if (random.nextBoolean()) {
                currentPlayer.getDamage(-6);
            } else {
                if (currentPlayer.getActiveCards().size() > 0) {
                    currentPlayer.getActiveCards().get(random.nextInt(opponent.getActiveCards().size())).changeStrengthBy(6);
                }
            }
        });
        Card gorycz = new SpecialCard(Books.DZIADY, "Losowy stronnik lub przeciwnik otrzymuje 7 obrażeń", "Gorycz", 6, (Card currentCard, Player currentPlayer, Player opponent) -> {
            if (random.nextBoolean()) {
                opponent.getDamage(7);
            } else {
                if (opponent.getActiveCards().size() > 0) {
                    opponent.getActiveCards().get(random.nextInt(opponent.getActiveCards().size())).hit(7);
                }
            }
        });
        Card aKysz = new SpecialCard(Books.DZIADY, "Niszczy wszystkie karty na polu biwy", "A Kysz!", 8, (Card currentCard, Player currentPlayer, Player opponent) -> {

            if (currentPlayer.getDeck().getActiveHeroes().size() > 0) {
                int times = currentPlayer.getActiveCards().size();
                for (int i = 0; i < times; i++) {
                    currentPlayer.getDeck().moveActiveCardTooCementary(currentPlayer.getActiveCards().get(0));
                }
            }

            if (opponent.getDeck().getActiveHeroes().size() > 0) {

                for (int i = 0; i < opponent.getActiveCards().size(); i++) {
                    opponent.getDeck().moveActiveCardTooCementary(opponent.getActiveCards().get(0));
                }
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
