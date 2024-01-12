package States.Window;

import Particle.Acid;
import Window.Board;

public class DrawAcidState extends ButtonState {
    public DrawAcidState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Acid(board, MouseY, MouseX));
    }
}
