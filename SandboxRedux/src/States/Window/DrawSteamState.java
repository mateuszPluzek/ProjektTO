package States.Window;

import Particle.*;
import Window.Board;

public class DrawSteamState extends ButtonState {

    public DrawSteamState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Steam(board, MouseY, MouseX));
    }
}
