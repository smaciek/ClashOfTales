package Game.Model.Phases;

import Game.GameControllerInterface;
import Game.Model.Board;
import Game.Model.Card;
import Game.Model.Phase;
import Game.Model.Player;


public class MainPhase implements Phase {



    public MainPhase(GameControllerInterface.FromModel controller, Player activePlayer) {
        System.out.println("Main Phase");
        controller.setMainPhase();
    }

    @Override
    public void startPhase(Player activePlayer, int roundNumber) {

    }

    @Override
    public void endPhase() {

    }


}
