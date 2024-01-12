package States.Window;

import Particle.Insulation;
import Window.Board;
public class DrawInsulationState extends ButtonState {

    public DrawInsulationState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Insulation(board, MouseY, MouseX));
    }
}