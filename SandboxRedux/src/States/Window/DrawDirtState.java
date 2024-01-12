package States.Window;

import Particle.Dirt;
import Window.Board;

public class DrawDirtState extends ButtonState {
    public DrawDirtState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Dirt(board, MouseY, MouseX));
    }
}
