package States.Window;

import Particle.Fire;
import Window.Board;

public class DrawFireState extends ButtonState {
    public DrawFireState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Fire(board, MouseY, MouseX));
    }
}
