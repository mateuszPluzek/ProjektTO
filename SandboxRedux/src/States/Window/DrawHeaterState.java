package States.Window;

import Particle.Heater;
import Particle.Virus;
import Window.Board;

public class DrawHeaterState extends ButtonState {
    public DrawHeaterState() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.setParticle(MouseY, MouseX, new Heater(board, MouseY, MouseX));
    }
}
