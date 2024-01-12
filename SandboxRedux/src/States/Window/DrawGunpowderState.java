package States.Window;

import Particle.Gunpowder;
import Window.Board;

public class DrawGunpowderState extends ButtonState {
    public DrawGunpowderState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Gunpowder(board, MouseY, MouseX));
    }
}
