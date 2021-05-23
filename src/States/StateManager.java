package States;

import Utility.Handler;

public class StateManager {
    public State gameState;
    public State menuState;
    private Handler handler;
    private static State currentState = null;

    public StateManager(Handler handler) {
        this.handler = handler;
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
    }

    //getter and setter
    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State state) {
        currentState = state;
    }
}
