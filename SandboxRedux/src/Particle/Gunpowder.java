package Particle;

import RandomNum.RandomEvent;
import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class Gunpowder extends Particle{
    public Gunpowder(Board board, int row, int col) {
        super(board, new Color(49, 45, 45), "Gunpowder", 4, new ParticleState(), row, col);
    }

    @Override
    public String gravityTick() {
        if(getSurrounding()[6].getType().equals("Air"))
            return "down";
        else if(getSurrounding()[5].getType().equals("Air") && getSurrounding()[3].getType().equals("Air")) {
            if(getSurrounding()[7].getType().equals("Air") && getSurrounding()[4].getType().equals("Air")) {
                return RandomEvent.eventChance(5) ? "leftdown" : "rightdown";
            }
            return "leftdown";
        }
        else if(getSurrounding()[7].getType().equals("Air") && getSurrounding()[4].getType().equals("Air"))
            return "rightdown";
        else
            return "";
    }

    @Override
    public void interactTick() {
        if(getSurrounding()[6].getType().equals("Water")) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Water(getBoard(), i, j));
            getBoard().setParticle(i + 1, j, new Gunpowder(getBoard(), getSurrounding()[6].getRow(), getSurrounding()[6].getCol()));
        }
        else if(getSurrounding()[6].getType().equals("Oil")) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Oil(getBoard(), i, j));
            getBoard().setParticle(i + 1, j, new Gunpowder(getBoard(), getSurrounding()[6].getRow(), getSurrounding()[6].getCol()));
        }
    }

    @Override
    public void selfTick() {
        if (getBoard().getTemp(getRow(), getCol()) > 300) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Fire(getBoard(), i, j));
            getBoard().setTemp(i, j, 150);
        }
    }
}
