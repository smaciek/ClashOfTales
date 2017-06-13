package Game.Model.Phases;

import Game.GameControllerInterface;
import Game.Model.Phase;
import Game.Model.Player;

/**
 * Klasa reprezentująca fazę ataku
 */

public class AttackPhase implements Phase {


    public AttackPhase(GameControllerInterface.FromModel controller, Player activePlayer) {
        controller.setAttackPhase();
    }

}
