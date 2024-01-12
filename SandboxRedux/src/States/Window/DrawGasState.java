package States.Window;

import Particle.NaturalGas;
import Window.Board;

public class DrawGasState extends ButtonState {
    public DrawGasState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new NaturalGas(board, MouseY, MouseX));
    }

}
