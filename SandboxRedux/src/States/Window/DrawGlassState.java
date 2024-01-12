package States.Window;

import Particle.Glass;
import Window.Board;

public class DrawGlassState extends ButtonState {
    public DrawGlassState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Glass(board, MouseY, MouseX));
    }
}
