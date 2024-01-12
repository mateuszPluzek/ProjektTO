package Particle;

import States.Particles.ParticleState;

import java.awt.*;
import Window.Board;

public abstract class Particle implements Observer{
    private Color colour;
    private String type;
    private int tempValue;
    private Particle[] surrounding;
    private int row, col;
    private ParticleState state;
    private Board board;

    protected Particle(Board board, Color colour, String type, int tempValue, ParticleState state, int row, int col) {
        this.board = board;
        this.colour = colour;
        this.type = type;
        this.tempValue = tempValue;
        this.row = row;
        this.col = col;
        surrounding = new Particle[8];
        this.state = state;
    }

//    Behaviour
    public String gravityTick() {
        //  implement how gravity effects the particle
        return "";
    }
    public void selfTick() {
        // effects on itself
        return;
    }

    public void interactTick() {
        return;
    }


//    observer duty
    public void update(Particle[] surrounding) {
        for(int i = 0; i < 8;i++) {
            this.surrounding[i] = surrounding[i];
        }
    }

    protected void disappear() {
        getBoard().setParticle(getRow(), getCol(), new Air(getBoard(), getRow(), getCol()));
    }

//    getters and setters

    public Color getColour() {
        return colour;
    }

    public String getType() {
        return type;
    }

    public int getTempValue() {
        return tempValue;
    }

    public ParticleState getState() {
        return state;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setState(ParticleState state) {
        this.state = state;
    }
    public void setColour(Color colour) {
        this.colour = colour;
    }
    public Particle[] getSurrounding() {
        return surrounding;
    }

    public Board getBoard() {
        return board;
    }
}
