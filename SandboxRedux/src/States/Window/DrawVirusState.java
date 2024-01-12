package States.Window;

import Particle.Virus;
import Window.Board;

public class DrawVirusState extends ButtonState {
    public DrawVirusState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Virus(board, MouseY, MouseX));
    }
}
