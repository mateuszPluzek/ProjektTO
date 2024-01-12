package Particle;

import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class Cooler extends Particle{
    public Cooler(Board board, int row, int col) {
        super(board, new Color(58, 118, 171), "Cooler", 2, new ParticleState(), row, col);
    }

    @Override
    public void selfTick() {
        getBoard().incTemp(getRow(), getCol(), -40);
    }

}
