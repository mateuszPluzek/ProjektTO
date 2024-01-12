package Particle;

import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class Steel extends Particle {
    public Steel(Board board, int row, int col) {
        super(board, new Color(54, 54, 54,50), "Steel", 4, new ParticleState(), row, col);
    }

    @Override
    public String gravityTick() {
        return "";
    }
}
