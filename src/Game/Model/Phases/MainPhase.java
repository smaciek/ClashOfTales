package Game.Model.Phases;

import Game.GameControllerInterface;
import Game.Model.Phase;
import Game.Model.Player;

/**
 * Klasa reprezentująca fazę używania kart
 */
public class MainPhase implements Phase {
    public MainPhase(GameControllerInterface.FromModel controller, Player activePlayer) {
        controller.setMainPhase();
    }

}
