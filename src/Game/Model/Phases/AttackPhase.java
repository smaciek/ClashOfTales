package Game.Model.Phases;

import Game.GameControllerInterface;
import Game.Model.Phase;
import Game.Model.Player;

public class AttackPhase implements Phase {


    public AttackPhase(GameControllerInterface.FromModel controller, Player activePlayer) {
        System.out.println("Attack Phase");
        controller.setAttackPhase();
    }

    @Override
    public void startPhase(Player activePlayer, int roundNumber) {

        System.out.println("Koniec fazy Ataku");

    }

    @Override
    public void endPhase() {

    }
}
