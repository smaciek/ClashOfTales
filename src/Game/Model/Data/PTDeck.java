package Game.Model.Data;


import Game.Model.*;
import Game.Model.Cards.HeroCard;
import Game.Model.Cards.SpecialCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa generująca talię "Pana Tadeusza"
 */

public class PTDeck implements DataProvider {
    Random random = new Random();

    @Override
    public Deck getDeck() {
        Deck deck = new Deck("Pan Tadeusz");


        Card protazy = new HeroCard(Books.PAN_TADEUSZ, "Jeżeli na polu walki znajduje się karta 'Gerwazy' obie karty dostają +2 siły", "Protazy", 1, 1, (Card currentCard, Player currentPlayer, Player opponent) -> {
            for (Card card : currentPlayer.getActiveCards()) {
                if (card.equals("Gerwazy")) {
                    if (currentCard instanceof HeroCard) ((HeroCard) currentCard).changeStrengthBy(2);
                    if (card instanceof HeroCard) ((HeroCard) card).changeStrengthBy(2);
                }
            }
        });
        Card gerwazy = new HeroCard(Books.PAN_TADEUSZ, "Jeżeli na polu walki znajduje się karta 'Protazy' obie karty dostają +2 siły", "Gerwazy", 1, 1, (Card currentCard, Player currentPlayer, Player opponent) -> {
            for (Card card : currentPlayer.getActiveCards()) {
                if (card.equals("Protazy")) {
                    if (currentCard instanceof HeroCard) ((HeroCard) currentCard).changeStrengthBy(2);
                    if (card instanceof HeroCard) ((HeroCard) card).changeStrengthBy(2);
                }
            }
        });
        Card podkomorzy = new HeroCard(Books.PAN_TADEUSZ, "Podkomorzy", 2, 2, null);
        Card hrabia = new HeroCard(Books.PAN_TADEUSZ, "Hrabia", 2, 1, null);
        Card ksiadzRobak = new HeroCard(Books.PAN_TADEUSZ, "Ksiądz Robak", 3, 3, null);
        Card asesor = new HeroCard(Books.PAN_TADEUSZ, "Gracz otrzymuje bonus 1 many w kolejnej rundzie", "Asesor", 3, 1, (Card currentCard, Player currentPlayer, Player opponent) -> {
            currentPlayer.setManaBonus(1);
        });
        Card jankiel = new HeroCard(Books.PAN_TADEUSZ, "Jankiel", 4, 2, null);
        Card zosia = new HeroCard(Books.PAN_TADEUSZ, "Zosia", 4, 5, (Card currentCard, Player currentPlayer, Player opponent) -> {
        });
        Card wojski = new HeroCard(Books.PAN_TADEUSZ, "Wojski", 4, 3, null);
        Card tadeuszSoplica = new HeroCard(Books.PAN_TADEUSZ, "Tadeusz Soplica", 5, 5, null);
        Card telimenna = new HeroCard(Books.PAN_TADEUSZ, "Telimena", 6, 2, null);
        Card sedzia = new HeroCard(Books.PAN_TADEUSZ, "Dostaje +1 do siły za każdego stronnika przeciwnika", "Sędzia", 6, 3, (Card currentCard, Player currentPlayer, Player opponent) -> {
            int strength = opponent.getActiveCards().size();
            ((HeroCard) currentCard).changeStrengthBy(strength);
        });
        Card podkmorzy = new HeroCard(Books.PAN_TADEUSZ, "Podkomorzy", 7, 9, (Card currentCard, Player currentPlayer, Player opponent) -> {
        });


        Card swiatyniaDumania = new SpecialCard(Books.DZIADY,"Dobierasz 2 karty", "Świątynia dumania", 2, (Card currentCard, Player currentPlayer, Player opponent) -> {
            if (currentPlayer.getDeck().getHand().size() < 5)currentPlayer.getDeck().drawCard();
            if (currentPlayer.getDeck().getHand().size() < 5)currentPlayer.getDeck().drawCard();
        });
        Card wSzeregu = new SpecialCard(Books.PAN_TADEUSZ, "Przywołuje na pole bitwy 3 Chłopów o sile 1", "W szeregu!", 7, (Card currentCard, Player currentPlayer, Player opponent) -> {
            if (currentPlayer.getDeck().getActiveHeroes().size() < 5)
                currentPlayer.getDeck().playItem(deck.getItems().remove(0));
            if (currentPlayer.getDeck().getActiveHeroes().size() < 5)
                currentPlayer.getDeck().playItem(deck.getItems().remove(0));
            if (currentPlayer.getDeck().getActiveHeroes().size() < 5)
                currentPlayer.getDeck().playItem(deck.getItems().remove(0));
        });
        Card podanoDoStolu = new SpecialCard(Books.PAN_TADEUSZ, "Niszczy losowego stronnika przeciwnika. Dodaje jego siłę losowamu stronnikowi gracza", "Podano do stołu", 5, (Card currentCard, Player currentPlayer, Player opponent) -> {
            if (opponent.getDeck().getActiveHeroes().size() > 0) {
                HeroCard cardToDestroy = opponent.getDeck().getActiveHeroes().get(random.nextInt(opponent.getDeck().getActiveHeroes().size()));
                int strength = cardToDestroy.getStrength();
                opponent.getDeck().moveActiveCardTooCementary(cardToDestroy);

                HeroCard cartToGainStrength = currentPlayer.getDeck().getActiveHeroes().get(random.nextInt(currentPlayer.getDeck().getActiveHeroes().size()));
                cartToGainStrength.changeStrengthBy(strength);
            }

        });
        Card grzybobranie = new SpecialCard(Books.PAN_TADEUSZ, "Wszyscy stronnicy gracze otrzymują 2 siły", "Grzybobranie", 6, (Card currentCard, Player currentPlayer, Player opponent) -> {
            for (HeroCard heroCard : currentPlayer.getDeck().getActiveHeroes()) {
                heroCard.changeStrengthBy(2);
            }
        });
        Card litwoOjczyznoMoja = new SpecialCard(Books.PAN_TADEUSZ, "Niszczy wszystkich stronników o siel 2 lub mniej", "Litwo! Ojczyzno moja", 7, (Card currentCard, Player currentPlayer, Player opponent) -> {


            List<HeroCard> list = new ArrayList<>();

            for (HeroCard heroCard : currentPlayer.getDeck().getActiveHeroes()) {
                if (heroCard.getStrength() <= 2) list.add(heroCard);
            }
            for (HeroCard heroCard : list) {
                currentPlayer.getDeck().getActiveHeroes().remove(heroCard);
            }
            list = new ArrayList<>();


            for (HeroCard heroCard : opponent.getDeck().getActiveHeroes()) {
                if (heroCard.getStrength() <= 2) list.add(heroCard);
            }

            for (HeroCard heroCard : list) {
                opponent.getDeck().getActiveHeroes().remove(heroCard);
            }
        });
        deck.add(protazy);
        deck.add(gerwazy);
        deck.add(podkomorzy);
        deck.add(hrabia);
        deck.add(ksiadzRobak);
        deck.add(asesor);
        deck.add(jankiel);
        deck.add(zosia);
        deck.add(wojski);
        deck.add(tadeuszSoplica);
        deck.add(telimenna);
        deck.add(sedzia);
        deck.add(podkmorzy);
        deck.add(swiatyniaDumania);
        deck.add(wSzeregu);
        deck.add(podanoDoStolu);
        deck.add(grzybobranie);
        deck.add(litwoOjczyznoMoja);

        makeItems(deck);
        return deck;
    }

    private void makeItems(Deck deck) {
        HeroCard chlop1 = new HeroCard(Books.PAN_TADEUSZ, "Chłop", 0, 1, null);
        HeroCard chlop2 = new HeroCard(Books.PAN_TADEUSZ, "Chłop", 0, 1, null);
        HeroCard chlop3 = new HeroCard(Books.PAN_TADEUSZ, "Chłop", 0, 1, null);
        deck.addItem(chlop1);
        deck.addItem(chlop2);
        deck.addItem(chlop3);
    }
}
