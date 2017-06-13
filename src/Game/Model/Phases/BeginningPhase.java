package Game.Model.Phases;


import Game.GameControllerInterface;
import Game.Model.Phase;
import Game.Model.Player;

public class BeginningPhase implements Phase {
    GameControllerInterface.FromModel controller;

    public BeginningPhase(GameControllerInterface.FromModel controller, Player activePlayer, int roundNumber) {
        System.out.println("Begining Phase");
        this.controller = controller;
        activePlayer.startRound(roundNumber+100);
        activePlayer.getDeck().drawCard();
        controller.setMana();
        controller.setActivePlayerLife();
        controller.setOpponentLife();
        controller.setBeginningPhase();
        controller.showBoard();
//        controller.changePhase();

    }

    @Override
    public void startPhase(Player activePlayer, int roundNumber) {

    }

    @Override
    public void endPhase() {

    }

}
