package Game.Model;

import Game.*;
import Game.Model.Cards.HeroCard;
import Game.Model.Data.DIIDeck;
import Game.Model.Data.PTDeck;
import Game.Model.Phases.AttackPhase;
import Game.Model.Phases.BeginningPhase;
import Game.Model.Phases.MainPhase;

import java.util.Random;

public class GameModel implements GameModelInterface {
    private Random random;
    private GameControllerInterface.FromModel controller;

    private Player player1;
    private Player player2;

    private Player activePlayer;
    private Player opponent;

    private Phase activePhase;

    private HeroCard activeCard = null;

//    private Board board;

    public GameModel(GameControllerInterface.FromModel controller, String player1Name, Decks player1Deck, String player2Name, Decks player2Deck) {
        this.controller = controller;
        this.player1 = new Player(player1Name, getDeck(player1Deck));
        this.player2 = new Player(player2Name, getDeck(player2Deck));
//        this.board = new Board(player1, player2);

        this.activePlayer = player1;
        this.opponent = player2;
    }




    @Override
    public void changePlayer(){
        if (activePlayer.equals(player1)){
            activePlayer = player2;
            opponent = player1;
        }else {
            activePlayer = player1;
            opponent = player2;
        }
    }


    @Override
    public boolean isSomeoneDead(){
        return activePlayer.getLife()<=0 || opponent.getLife() <= 0;
    }


    @Override
    public Player getPlayer1() {
        return player1;
    }


    @Override
    public Player getPlayer2() {
        return player2;
    }

    @Override
    public Deck getPlayer1Deck() {
        return player1.getDeck();
    }

    @Override
    public Deck getPlayer2Deck() {
        return player2.getDeck();
    }

    @Override
    public Player getActivePlayer() {
        return activePlayer;
    }

    @Override
    public Player getOpponent() {
        return opponent;
    }

    private Deck getDeck(Decks decks){
        DataProvider dataProvider;
        switch (decks){
            case DZIADY:
                dataProvider = new DIIDeck();
                break;
            case PAN_TADEUSZ:
                dataProvider = new PTDeck();
                break;

            default:
                if(random.nextBoolean()){
                    dataProvider = new DIIDeck();
                }else {
                    dataProvider = new PTDeck();
                }

        }
        return dataProvider.getDeck();
    }

    public Card getActiveCard() {
        return activeCard;
    }

    public void setActiveCard(HeroCard activeCard) {
        this.activeCard = activeCard;
    }

    public void fight(HeroCard selectedCard){

        int activeCardStrength = activeCard.getStrength();
        int selectedCardStrength = selectedCard.getStrength();

        if(!selectedCard.hit(activeCardStrength)){
            getOpponent().getDeck().moveActiveCardTooCementary(selectedCard);
        }
        if(!activeCard.hit(selectedCardStrength)){
            getActivePlayer().getDeck().moveActiveCardTooCementary(activeCard);
        }

        controller.showBoard();

        activeCard = null;
    }

    @Override
    public void untapAll() {
        for (HeroCard heroCard: activePlayer.getDeck().getActiveHeroes()) {
            heroCard.setTapped(false);
        }
    }
}
