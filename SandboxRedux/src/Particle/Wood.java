package Particle;

import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class Wood extends Particle{
    public Wood(Board board, int row, int col) {
        super(board, new Color(72, 46, 35), "Wood", 2, new ParticleState(), row, col);
    }

    @Override
    public String gravityTick() {
        return "";
    }

    @Override
    public void selfTick() {
        if(getBoard().getTemp(getRow(), getCol()) > 200) {
            if(!getSurrounding()[0].getType().equals("Air") || !getSurrounding()[1].getType().equals("Air") || !getSurrounding()[2].getType().equals("Air")) {
                getBoard().incTemp(getRow(), getCol(), 100);
                getBoard().setParticle(getRow(), getCol(), new Ash(getBoard(), getRow(), getCol()));
            }
            else {
                getBoard().setParticle(getSurrounding()[1].getRow(), getSurrounding()[1].getCol(), new Fire(getBoard(), getSurrounding()[1].getRow(), getSurrounding()[1].getCol()));
                getBoard().incTemp(getRow(), getCol(), 25);
            }
        }
    }
}
