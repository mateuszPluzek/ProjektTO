package States.Window;

import Particle.Cooler;
import Particle.Virus;
import Window.Board;

public class DrawCoolerState extends ButtonState {
    public DrawCoolerState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Cooler(board, MouseY, MouseX));
    }
}
