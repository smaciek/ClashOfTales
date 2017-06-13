package Game.Model.Phases;


import Game.GameControllerInterface;
import Game.Model.Phase;
import Game.Model.Player;

/**
 * Klasa reprezentująca fazę przygotowania
 */

public class BeginningPhase implements Phase {
    GameControllerInterface.FromModel controller;

    public BeginningPhase(GameControllerInterface.FromModel controller, Player activePlayer, int roundNumber) {
        this.controller = controller;
        activePlayer.startRound(roundNumber + 100);
        activePlayer.getDeck().drawCard();
        controller.setMana();
        controller.setActivePlayerLife();
        controller.setOpponentLife();
        controller.setBeginningPhase();
        controller.showBoard();

    }


}
