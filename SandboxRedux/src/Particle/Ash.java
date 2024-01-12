package Particle;

import RandomNum.RandomEvent;
import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class Ash extends Particle{
    public Ash(Board board, int row, int col) {
        super(board, new Color(107, 107, 107), "Ash", 4, new ParticleState(), row, col);
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

    public void interactTick() {
        if(getSurrounding()[6].getType().equals("Water")) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Water(getBoard(), i, j));
            getBoard().setParticle(i + 1, j, new Ash(getBoard(), getSurrounding()[6].getRow(), getSurrounding()[6].getCol()));
        }
        else if(getSurrounding()[6].getType().equals("Oil")) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Oil(getBoard(), i, j));
            getBoard().setParticle(i + 1, j, new Ash(getBoard(), getSurrounding()[6].getRow(), getSurrounding()[6].getCol()));
        }
    }
}
