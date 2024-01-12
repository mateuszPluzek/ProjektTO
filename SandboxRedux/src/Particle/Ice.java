package Particle;

import States.Particles.ParticleState;
import Window.Board;

import java.awt.*;

public class Ice extends Particle{
    public Ice(Board board, int row, int col) {
        super(board, new Color(9, 210, 250), "Ice", 6, new ParticleState(), row, col);
    }

    @Override
    public String gravityTick() {
        return "";
    }

    @Override
    public void interactTick() {
//        for(int i = 0; i < 8;i++) {
//            if(!getSurrounding()[i].getType().equals("Edge"))
//                getBoard().incTemp(getSurrounding()[i].getRow(), getSurrounding()[i].getCol(), -5);
//        }
    }

    @Override
    public void selfTick() {

        if(getBoard().getTemp(getRow(), getCol()) > -10) {
            int j = getCol();
            int i = getRow();
            getBoard().setParticle(i, j, new Water(getBoard(), i, j));
        }
    }
}
