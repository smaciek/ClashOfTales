package Game.Model.Phases;

import Game.GameControllerInterface;
import Game.Model.Phase;
import Game.Model.Player;

/**
 * Klasa repprezentująca fazę podsumowania
 */
public class EvaluationPhase implements Phase {

    public EvaluationPhase(GameControllerInterface.FromModel controller, Player activePlayer) {
        controller.setEvaluationPhase();
        controller.untap();
    }

}
