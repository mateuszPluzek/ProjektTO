package States.Window;

import Particle.*;
import Window.Board;

public class DrawWaterState extends ButtonState {

    public DrawWaterState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Water(board, MouseY, MouseX));
    }

}
