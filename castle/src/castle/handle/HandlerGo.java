package castle.handle;

import castle.Game;

public class HandlerGo extends Handler {
    private Game game;

    public HandlerGo(Game game){
        this.game = game;
    }

    @Override
    public void doCmd(String word) {
        game.goRoom(word);
    }
}
