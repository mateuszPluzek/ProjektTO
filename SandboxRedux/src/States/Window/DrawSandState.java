package States.Window;

import Particle.*;
import Window.Board;

public class DrawSandState extends ButtonState {

    public DrawSandState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Sand(board, MouseY, MouseX));
    }

}
