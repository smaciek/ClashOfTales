package Game.Model.Data;


import Game.Model.*;
import Game.Model.Cards.HeroCard;
import Game.Model.Cards.SpecialCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PTDeck implements DataProvider {
    Random random = new Random();
    @Override
    public Deck getDeck(){
        Deck deck = new Deck("Pan Tadeusz");

        Card protazy = new HeroCard(Books.PAN_TADEUSZ, "Protazy", 1, 1, (Card currentCard, Player currentPlayer, Player opponent)->{
            for (Card card:currentPlayer.getActiveCards()) {
                if (card.equals("Gerwazy")){
                    if (currentCard instanceof HeroCard)((HeroCard) currentCard).changeStrengthBy(2);
                    if (card instanceof HeroCard)((HeroCard) card).changeStrengthBy(2);
                }
            }
        });
        Card gerwazy = new HeroCard(Books.PAN_TADEUSZ, "Gerwazy", 1, 1, (Card currentCard, Player currentPlayer, Player opponent)->{
            for (Card card:currentPlayer.getActiveCards()) {
                if (card.equals("Protazy")){
                    if (currentCard instanceof HeroCard)((HeroCard) currentCard).changeStrengthBy(2);
                    if (card instanceof HeroCard)((HeroCard) card).changeStrengthBy(2);
                }
            }
        });
        Card podkomorzy = new HeroCard(Books.PAN_TADEUSZ, "Podkomorzy", 2, 2, null);
        Card hrabia = new HeroCard(Books.PAN_TADEUSZ, "Hrabia", 2, 1, null);
        Card ksiadzRobak = new HeroCard(Books.PAN_TADEUSZ, "Ksiądz Robak", 3, 3, null);
        Card asesor = new HeroCard(Books.PAN_TADEUSZ, "Asesor", 3, 1, (Card currentCard, Player currentPlayer, Player opponent)->{
            currentPlayer.setManaBonus(1);
        });
        Card jankiel = new HeroCard(Books.PAN_TADEUSZ, "Jankiel", 4, 2,null);
        Card zosia = new HeroCard(Books.PAN_TADEUSZ, "Zosia", 4, 5, (Card currentCard, Player currentPlayer, Player opponent)->{});
        Card wojski = new HeroCard(Books.PAN_TADEUSZ, "Wojski", 4, 3 ,null);
        Card tadeuszSoplica = new HeroCard(Books.PAN_TADEUSZ, "Tadeusz Soplica", 5, 5, null);
        Card telimenna = new HeroCard(Books.PAN_TADEUSZ, "Telimena", 6 ,2, null);
        Card sedzia = new HeroCard(Books.PAN_TADEUSZ, "Sędzia", 6, 3, (Card currentCard, Player currentPlayer, Player opponent)->{
            int strength = opponent.getActiveCards().size();
            ((HeroCard)currentCard).changeStrengthBy(strength);
        });
        Card podkmorzy = new HeroCard(Books.PAN_TADEUSZ, "Podkomorzy", 7, 9, (Card currentCard, Player currentPlayer, Player opponent)->{});





        Card swiatyniaDumania = new SpecialCard(Books.DZIADY,"Świątynia dumania", 2, (Card currentCard, Player currentPlayer, Player opponent)->{
            currentPlayer.getDeck().drawCard();
            currentPlayer.getDeck().drawCard();

            /*TODO:
            *Odrzucenie karty
            * */
        });
        Card wSzeregu = new SpecialCard(Books.PAN_TADEUSZ, "W szeregu!", 7, (Card currentCard, Player currentPlayer, Player opponent)->{
            currentPlayer.getDeck().playItem(deck.getItems().remove(0));
            currentPlayer.getDeck().playItem(deck.getItems().remove(0));
            currentPlayer.getDeck().playItem(deck.getItems().remove(0));
        });
        Card podanoDoStolu = new SpecialCard(Books.PAN_TADEUSZ, "Podano do stołu", 5, (Card currentCard, Player currentPlayer, Player opponent)->{
            if (opponent.getDeck().getActiveHeroes().size()>0) {
                HeroCard cardToDestroy = opponent.getDeck().getActiveHeroes().get(random.nextInt(opponent.getDeck().getActiveHeroes().size()));
                int strength = cardToDestroy.getStrength();
                opponent.getDeck().moveActiveCardTooCementary(cardToDestroy);

                HeroCard cartToGainStrength = currentPlayer.getDeck().getActiveHeroes().get(random.nextInt(currentPlayer.getDeck().getActiveHeroes().size()));
                cartToGainStrength.changeStrengthBy(strength);
            }

        });
        Card grzybobranie = new SpecialCard(Books.PAN_TADEUSZ, "Grzybobranie", 6, (Card currentCard, Player currentPlayer, Player opponent)->{
            for (HeroCard heroCard:currentPlayer.getDeck().getActiveHeroes()) {
                heroCard.changeStrengthBy(2);
            }
        });
        Card litwoOjczyznoMoja = new SpecialCard(Books.PAN_TADEUSZ, "Litwo! Ojczyzno moja", 7, (Card currentCard, Player currentPlayer, Player opponent)->{


            List<HeroCard> list = new ArrayList<>();

            for (HeroCard heroCard:currentPlayer.getDeck().getActiveHeroes()) {
                if(heroCard.getStrength()<=2)list.add(heroCard);
            }
            for (HeroCard heroCard: list) {
                currentPlayer.getDeck().getActiveHeroes().remove(heroCard);
            }
            list = new ArrayList<>();


            for (HeroCard heroCard:opponent.getDeck().getActiveHeroes()) {
                if (heroCard.getStrength()<=2)list.add(heroCard);
            }

            for (HeroCard heroCard: list) {
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

    private void makeItems(Deck deck){
        HeroCard chlop1 = new HeroCard(Books.PAN_TADEUSZ, "Chłop", 0, 1, null);
        HeroCard chlop2 = new HeroCard(Books.PAN_TADEUSZ, "Chłop", 0, 1, null);
        HeroCard chlop3 = new HeroCard(Books.PAN_TADEUSZ, "Chłop", 0, 1, null);
        deck.addItem(chlop1);
        deck.addItem(chlop2);
        deck.addItem(chlop3);
    }
}
