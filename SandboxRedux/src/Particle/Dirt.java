package Particle;

import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class Dirt extends Particle{
    public Dirt(Board board, int row, int col) {
        super(board, new Color(131, 62, 9), "Dirt", 6, new ParticleState(), row, col);
    }

    @Override
    public String gravityTick() {
        if(getSurrounding()[6].getType().equals("Air"))
            return "down";
        else
            return "";
    }


    @Override
    public void interactTick() {
        if(getSurrounding()[6].getType().equals("Water")) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Water(getBoard(), i, j));
            getBoard().setParticle(i + 1, j, new Dirt(getBoard(), getSurrounding()[6].getRow(), getSurrounding()[6].getCol()));
        }
        else if(getSurrounding()[6].getType().equals("Oil")) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Oil(getBoard(), i, j));
            getBoard().setParticle(i + 1, j, new Dirt(getBoard(), getSurrounding()[6].getRow(), getSurrounding()[6].getCol()));
        }
    }
}
