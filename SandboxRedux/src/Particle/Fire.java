package Particle;

import RandomNum.RandomEvent;
import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class Fire extends Particle{
    public Fire(Board board, int row, int col) {
        super(board, Color.RED, "Fire", 1, new ParticleState(), row, col);
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
        if(getSurrounding()[1].getType().equals("Edge"))
            disappear();
        if(getSurrounding()[1].getType().equals("Steam")) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Steam(getBoard(), i, j));
            getBoard().setParticle(i - 1, j, new Fire(getBoard(), getSurrounding()[1].getRow(), getSurrounding()[1].getCol()));
        }
    }

    @Override
    public void selfTick() {

        getBoard().incTemp(getRow(), getCol(), 50);

        if(getBoard().getTemp(getRow(), getCol()) < 100)
            disappear();

        if(RandomEvent.eventChance(3)) {
            setColour(new Color(0xF52121));
        }
        else if(RandomEvent.eventChance(3)) {
            setColour(new Color(0xFF0707));
        }
        else {
            setColour(new Color(0xE54509));
        }
    }
}
