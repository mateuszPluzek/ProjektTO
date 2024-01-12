package Particle;

import RandomNum.RandomEvent;
import States.Particles.DrySand;
import States.Particles.ParticleState;
import States.Particles.WetSand;
import Window.Board;

import java.awt.*;

public class Water extends Particle {
    public Water(Board board, int row, int col) {
        super(board, Color.BLUE, "Water", 8, new ParticleState(), row, col);
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

        if(getSurrounding()[6].getType().equals("Oil")) {
            int i = getRow();
            int j = getCol();
            getBoard().setParticle(i, j, new Oil(getBoard(), i, j));
            getBoard().setParticle(i + 1, j, new Water(getBoard(), getSurrounding()[6].getRow(), getSurrounding()[6].getCol()));
        }

        for(int n = 0; n <= 7; n++) {
            if(getSurrounding()[n].getType().equals("Sand") && getSurrounding()[n].getState() instanceof DrySand) {
                getSurrounding()[n].setState(new WetSand());
                getSurrounding()[n].setColour(new Color(161, 161, 19));
                disappear();
            }
            else if(getSurrounding()[n].getType().equals("Dirt")) {
                getBoard().setParticle(getSurrounding()[n].getRow(), getSurrounding()[n].getCol(), new Mud(getBoard(), getSurrounding()[n].getRow(), getSurrounding()[n].getCol()));
                disappear();
            }
        }
    }

    @Override
    public void selfTick() {

        if(getBoard().getTemp(getRow(), getCol()) > 50) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Steam(getBoard(), i, j));
            getBoard().setTemp(i, j, -8);
        }
        else if(getBoard().getTemp(getRow(), getCol()) < -20) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Ice(getBoard(), i, j));
        }

    }

}
