package Particle;

import States.Particles.ParticleState;
import Window.Board;
import java.awt.*;

public class Air extends Particle{

    public Air(Board board, int row, int col) {
        super(board, Color.BLACK, "Air", 4, new ParticleState(), row, col);
    }

}
