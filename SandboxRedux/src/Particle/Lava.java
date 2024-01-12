package Particle;

import RandomNum.RandomEvent;
import States.Particles.DrySand;
import States.Particles.ParticleState;
import States.Particles.WetSand;
import Window.Board;

import java.awt.*;

public class Lava extends Particle{

    public Lava(Board board, int row, int col) {
        super(board, new Color(0xE51D00), "Lava", 8, new ParticleState(), row, col);
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
        else if(getSurrounding()[3].getType().equals("Air") && getSurrounding()[4].getType().equals("Air"))
            return RandomEvent.eventChance(5) ? "left" : "right";
        else if(getSurrounding()[3].getType().equals("Air"))
            return "left";
        else if(getSurrounding()[4].getType().equals("Air"))
            return "right";
        else
            return "";
    }

    public void selfTick() {

        if(getBoard().getTemp(getRow(), getCol()) < 1000) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Stone(getBoard(), i, j));
        }
        else
            getBoard().incTemp(getRow(), getCol(), 20);

    }
}
