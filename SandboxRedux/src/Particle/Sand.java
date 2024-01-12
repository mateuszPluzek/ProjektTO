package Particle;

import RandomNum.RandomEvent;
import States.Particles.DrySand;
import Window.Board;
import java.awt.*;

public class Sand extends Particle{

    public Sand(Board board, int row, int col) {
        super(board, Color.YELLOW, "Sand", 2, new DrySand(), row, col);
    }

    @Override
    public String gravityTick() {
        return this.getState().gravityTick(this.getSurrounding());
    }
    @Override
    public void selfTick() {
        this.getState().selfTick(this);
    }

    @Override
    public void interactTick() {
        this.getState().interactTick(this.getSurrounding(), this);
    }
}