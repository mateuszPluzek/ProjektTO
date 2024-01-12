package Particle;

import RandomNum.RandomEvent;
import States.Particles.DrySand;
import States.Particles.ParticleState;
import States.Particles.WetSand;
import Window.Board;

import java.awt.*;

public class Virus extends Particle {
    public Virus(Board board, int row, int col) {
        super(board, new Color(0xA34CE3), "Virus", 4, new ParticleState(), row, col);
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
        for(int n = 0; n <= 7; n++) {
            if(!(getSurrounding()[n].getType().equals("Air") || getSurrounding()[n].getType().equals("Edge"))) {
                getBoard().setParticle(getSurrounding()[n].getRow(), getSurrounding()[n].getCol(), new Virus(getBoard(), getSurrounding()[n].getRow(), getSurrounding()[n].getCol()));
            }
        }
    }


}
