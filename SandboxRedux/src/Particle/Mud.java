package Particle;

import States.Particles.DrySand;
import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class Mud extends Particle {
    public Mud(Board board, int row, int col) {
        super(board, new Color(77, 34, 5), "Mud", 6, new ParticleState(), row, col);
    }

    @Override
    public String gravityTick() {
        if(getSurrounding()[6].getType().equals("Air"))
            return "down";
        else
            return "";
    }

    @Override
    public void selfTick() {
        if(getBoard().getTemp(getRow(), getCol()) > 110 && getSurrounding()[1].getType().equals("Air")) {
            getBoard().setParticle(getRow(), getCol(), new Dirt(getBoard(), getRow(), getCol()));
            getBoard().setParticle(getSurrounding()[1].getRow(), getSurrounding()[1].getCol(),new Steam(getBoard(), getSurrounding()[1].getRow(), getSurrounding()[1].getCol()));
        }
    }

    @Override
    public void interactTick() {
        if(getSurrounding()[6].getType().equals("Water")) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Water(getBoard(), i, j));
            getBoard().setParticle(i + 1, j, new Mud(getBoard(), getSurrounding()[6].getRow(), getSurrounding()[6].getCol()));
        }
        else if(getSurrounding()[6].getType().equals("Oil")) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Oil(getBoard(), i, j));
            getBoard().setParticle(i + 1, j, new Mud(getBoard(), getSurrounding()[6].getRow(), getSurrounding()[6].getCol()));
        }
    }
}
