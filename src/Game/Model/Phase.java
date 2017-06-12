package Game.Model;


public interface Phase {
    void startPhase(Player activePlayer, int roundNumber);
    void endPhase();
}
