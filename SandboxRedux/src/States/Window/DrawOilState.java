package States.Window;

import Particle.Oil;
import Window.Board;

public class DrawOilState extends ButtonState {
    public DrawOilState() {
        super();
    }

    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Oil(board, MouseY, MouseX));
    }

}
