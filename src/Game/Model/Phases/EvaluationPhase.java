package Game.Model.Phases;

import Game.GameControllerInterface;
import Game.Model.Phase;
import Game.Model.Player;


public class EvaluationPhase implements Phase {

    public EvaluationPhase(GameControllerInterface.FromModel controller, Player activePlayer) {
        System.out.println("Evaluation Phase");

        controller.setEvaluationPhase();
        controller.untap();
    }

    @Override
    public void startPhase(Player activePlayer, int roundNumber) {

    }

    @Override
    public void endPhase() {

    }
}
