package Particle;

import RandomNum.RandomEvent;
import States.Particles.DrySand;
import States.Particles.ParticleState;
import States.Particles.WetSand;
import Window.Board;

import java.awt.*;

public class Acid extends Particle {

    public Acid(Board board, int row, int col) {
        super(board, new Color(0x0ABB0A), "Acid", 4, new ParticleState(), row, col);
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

    @Override
    public void interactTick() {

        for(int n = 0; n <= 7; n++) {
            if (!(getSurrounding()[n].getType().equals("Air")||getSurrounding()[n].getType().equals("Edge")||getSurrounding()[n].getType().equals("Acid")||getSurrounding()[n].getType().equals("Glass"))) {
                int j = getSurrounding()[n].getCol();
                int i = getSurrounding()[n].getRow();
                getBoard().setParticle(i, j, new Air(getBoard(), i, j));
                disappear();
            }
        }
    }

}
