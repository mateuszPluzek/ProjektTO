package Particle;

import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class Stone extends Particle{
    //TODO changes into lava whean heated
    public Stone(Board board, int row, int col) {
        super(board, Color.GRAY, "Glass", 8, new ParticleState(), row, col);
    }

    @Override
    public String gravityTick() {
        return "";
    }

    @Override
    public void selfTick() {
        if(getBoard().getTemp(getRow(), getCol()) > 1000) {
            getBoard().setParticle(getRow(), getCol(), new Lava(getBoard(), getRow(), getCol()));
        }
    }
}
