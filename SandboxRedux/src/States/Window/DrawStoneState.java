package States.Window;

import Particle.Stone;
import Window.Board;

public class DrawStoneState extends ButtonState {
    public DrawStoneState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Stone(board, MouseY, MouseX));
    }
}
