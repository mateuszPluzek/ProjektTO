package Particle;

import RandomNum.RandomEvent;
import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class NaturalGas extends Particle{

    public NaturalGas(Board board, int row, int col) {
        super(board, new Color(255, 250, 195), "NaturalGas", 2, new ParticleState(), row, col);
    }

    @Override
    public String gravityTick() {
        if(getSurrounding()[1].getType().equals("Air")) {
            if(getSurrounding()[0].getType().equals("Air") && getSurrounding()[3].getType().equals("Air")) {
                if(getSurrounding()[2].getType().equals("Air")  && getSurrounding()[4].getType().equals("Air")) {
                    if(RandomEvent.eventChance(3))
                        return "leftup";
                    else if(RandomEvent.eventChance(3))
                        return "rightup";
                    else
                        return "up";
                }
                else
                    return RandomEvent.eventChance(5) ? "leftup" : "up";
            }
            else
                return "up";
        }
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
        if(getSurrounding()[1].getType().equals("Water")) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Water(getBoard(), i, j));
            getBoard().setParticle(i - 1, j, new NaturalGas(getBoard(), getSurrounding()[1].getRow(), getSurrounding()[1].getCol()));
        }
        else if(getSurrounding()[1].getType().equals("Oil")) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Oil(getBoard(), i, j));
            getBoard().setParticle(i - 1, j, new NaturalGas(getBoard(), getSurrounding()[1].getRow(), getSurrounding()[1].getCol()));
        }
    }

    @Override
    public void selfTick() {
        if (getBoard().getTemp(getRow(), getCol()) > 300) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Fire(getBoard(), i, j));
            getBoard().setTemp(i, j, 100);
        }

    }

}
