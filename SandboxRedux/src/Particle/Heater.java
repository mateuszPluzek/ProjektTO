package Particle;

import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class Heater extends Particle{
    public Heater(Board board, int row, int col) {
        super(board, new Color(75, 0, 0), "Heater", 2, new ParticleState(), row, col);
    }

    @Override
    public void selfTick() {
        getBoard().incTemp(getRow(), getCol(), 40);
    }
}
