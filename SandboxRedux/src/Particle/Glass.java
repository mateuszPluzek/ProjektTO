package Particle;

import States.Particles.ParticleState;
import Window.Board;
import java.awt.*;

public class Glass extends Particle{

    public Glass(Board board, int row, int col) {
        super(board, new Color(255,255,255,50), "Glass", 2, new ParticleState(), row, col);
    }

    @Override
    public String gravityTick() {
        return "";
    }
}