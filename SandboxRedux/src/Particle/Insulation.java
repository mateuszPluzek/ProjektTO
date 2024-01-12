package Particle;

import States.Particles.ParticleState;
import Window.Board;
import java.awt.*;

public class Insulation extends Particle{

    public Insulation(Board board, int row, int col) {
        super(board, new Color(168, 130, 10,50), "Insulation", 2, new ParticleState(), row, col);
    }

    @Override
    public String gravityTick() {
        return "";
    }
}