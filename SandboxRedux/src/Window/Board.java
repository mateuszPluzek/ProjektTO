package Window;

import Particle.*;
import RandomNum.RandomEvent;
import memento.History;
import memento.Memento;

import java.util.ArrayList;

public class Board {
    private int rows;
    private int cols;

    private int gridSize;
    private Particle[][] board; // listeners
//    temperature board
    private int[][] tempField;
    private ArrayList<TargetRec> ignoreList = new ArrayList<>();
    private History history;

    public Board(int height, int width, int gridSize) {

        this.history = new History();
        this.gridSize = gridSize;

        this.rows = height / gridSize;
        this.cols = width / gridSize;

        board = new Particle[rows][cols];
        tempField = new int[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                board[i][j] = new Air(this,i, j);
                tempField[i][j] = 10;
            }
        }
    }

    public Board(Board newBoard) {
        this.history = new History();
        this.gridSize = newBoard.getGridSize();
        this.rows = newBoard.getRows();
        this.cols = newBoard.getCols();

        board = new Particle[rows][cols];
        tempField = new int[rows][cols];

        for(int i = 0;i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                board[i][j] = newBoard.getParticle(i, j);
                tempField[i][j] = getTemp(i, j);
            }
        }
    }

    public void cycle() {

        this.ignoreList.clear();
        boolean skip;

        for(int i = rows - 1; i >= 0; i--) {
            for(int j = 0; j < cols; j++) {

                transferHeat(i,j);

                skip = false;
                for(TargetRec t : ignoreList) {
                    if(t.getI() == i && t.getJ() == j) {
                        skip = true;
                        break;
                    }
                }
                board[i][j].update(generateSurrounding(this, i, j));
                board[i][j].interactTick();
                board[i][j].update(generateSurrounding(this, i, j));
                board[i][j].selfTick();
                if(!skip) {
                    board[i][j].update(generateSurrounding(this, i, j));
                    move(i, j, board[i][j].gravityTick());
                }
            }
        }
    }

    public Board backup() {
        return new Board(this);
    }

    public void restore(Board backup) {
        this.gridSize = gridSize;

        this.rows = backup.getRows();
        this.cols = backup.getCols();

        board = new Particle[rows][cols];
        tempField = new int[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                board[i][j] = backup.getParticle(i, j);
                tempField[i][j] = backup.getTemp(i, j);
            }
        }
    }




    public void move(int i, int j, String dir) {
        BoardServices.move(i, j, dir, tempField, board, this, ignoreList);
    }

    public Particle[] generateSurrounding(Board board, int i, int j) {
        return BoardServices.generateSurrounding(board, i, j);
    }

    private void transferHeat(int row, int col) {
        BoardServices.transferHeat(row, col, tempField, rows, cols, board);
    }

    public Particle getParticle(int row, int col) {
        return this.board[row][col];
    }
    public void setParticle(int row, int col, Particle particle) {
        board[row][col] = particle;
    }
    public int getTemp(int row, int col) {
        return this.tempField[row][col];
    }

    public void incTemp(int row, int col, int value) {
        tempField[row][col] += value;
    }

    public void setTemp(int row, int col, int val) {
        tempField[row][col] = val;
    }


    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getGridSize() {
        return gridSize;
    }

    public History getHistory() {
        return history;
    }
}
