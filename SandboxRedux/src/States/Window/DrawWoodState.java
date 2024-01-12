package States.Window;

import Particle.Wood;
import Window.Board;
import Window.ButtonList;

public class DrawWoodState extends ButtonState {
    public DrawWoodState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Wood(board, MouseY, MouseX));
    }

}
