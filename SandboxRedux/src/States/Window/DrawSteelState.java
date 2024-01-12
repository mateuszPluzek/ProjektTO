package States.Window;

import Particle.Steel;
import Window.Board;

public class DrawSteelState extends ButtonState {
    public DrawSteelState() {
        super();
    }

    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Steel(board, MouseY, MouseX));
    }

}
