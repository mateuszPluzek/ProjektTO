package States.Window;

import Particle.Air;
import Window.Board;

public class DrawAirState extends ButtonState {
    public DrawAirState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Air(board, MouseY, MouseX));
    }
}
