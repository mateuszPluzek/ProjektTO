package Window;

import Particle.*;
import Particle.Edge;

import java.util.ArrayList;

public class BoardServices {

//    cleaner code put methods as static
    public static Particle[] generateSurrounding(Board board, int i, int j) {
        Particle[] surrounding = new Particle[8];
        int di[] = {-1, -1, -1, 0, 0, 1, 1, 1};
        int dj[] = {-1, 0, 1, -1, 1, -1, 0, 1};

        for(int ii = 0; ii < 8; ii++) {
            int newRow = i + di[ii];
            int newCol = j + dj[ii];
    //        check if position is inside the bounds
            if (newRow >= 0 && newRow < board.getRows() && newCol >= 0 && newCol < board.getCols())
                surrounding[ii] = board.getParticle(newRow, newCol);
            else
                surrounding[ii] = new Edge(board);
        }
        return surrounding;
    }

    public static void move(int i, int j, String dir, int[][] tempField, Particle[][] board, Board self, ArrayList<TargetRec> ignoreList) {
        int tmp;
        switch (dir) {
            case "down":
                tmp = tempField[i + 1][j];
                tempField[i + 1][j] = tempField[i][j];
                tempField[i][j] = tmp;

                board[i + 1][j] = board[i][j];
                board[i + 1][j].setRow((i + 1));
                board[i][j] = new Air(self, i, j);
                ignoreList.add(new TargetRec(i + 1, j));
                break;
            case "up":
                tmp = tempField[i - 1][j];
                tempField[i - 1][j] = tempField[i][j];
                tempField[i][j] = tmp;

                board[i - 1][j] = board[i][j];
                board[i - 1][j].setRow((i - 1));
                board[i][j] = new Air(self, i, j);
                ignoreList.add(new TargetRec(i - 1, j));
                break;
            case "left":
                tmp = tempField[i][j - 1];
                tempField[i][j - 1] = tempField[i][j];
                tempField[i][j] = tmp;

                board[i][j - 1] = board[i][j];
                board[i][j - 1].setCol((j - 1));
                board[i][j] = new Air(self, i, j);
                ignoreList.add(new TargetRec(i, j - 1));
                break;
            case "right":
                tmp = tempField[i][j + 1];
                tempField[i][j + 1] = tempField[i][j];
                tempField[i][j] = tmp;

                board[i][j + 1] = board[i][j];
                board[i][j + 1].setCol((j + 1));
                board[i][j] = new Air(self, i, j);
                ignoreList.add(new TargetRec(i, j + 1));
                break;
            case "leftup":
                tmp = tempField[i - 1][j - 1];
                tempField[i - 1][j - 1] = tempField[i][j];
                tempField[i][j] = tmp;

                board[i - 1][j - 1] = board[i][j];
                board[i - 1][j - 1].setCol((j - 1));
                board[i - 1][j - 1].setRow((i - 1));
                board[i][j] = new Air(self, i, j);
                ignoreList.add(new TargetRec(i - 1, j - 1));
                break;
            case "rightup":
                tmp = tempField[i - 1][j + 1];
                tempField[i - 1][j + 1] = tempField[i][j];
                tempField[i][j] = tmp;

                board[i - 1][j + 1] = board[i][j];
                board[i - 1][j + 1].setCol((j + 1));
                board[i - 1][j + 1].setRow((i - 1));
                board[i][j] = new Air(self, i, j);
                ignoreList.add(new TargetRec(i - 1, j + 1));
                break;
            case "leftdown":
                tmp = tempField[i + 1][j - 1];
                tempField[i + 1][j - 1] = tempField[i][j];
                tempField[i][j] = tmp;

                board[i + 1][j - 1] = board[i][j];
                board[i + 1][j - 1].setCol((j - 1));
                board[i + 1][j - 1].setRow((i + 1));
                board[i][j] = new Air(self, i, j);
                ignoreList.add(new TargetRec(i + 1, j - 1));
                break;
            case "rightdown":
                tmp = tempField[i + 1][j + 1];
                tempField[i + 1][j + 1] = tempField[i][j];
                tempField[i][j] = tmp;

                board[i + 1][j + 1] = board[i][j];
                board[i + 1][j + 1].setCol((j + 1));
                board[i + 1][j + 1].setRow((i + 1));
                board[i][j] = new Air(self, i, j);
                ignoreList.add(new TargetRec(i + 1, j + 1));
                break;
            default:
        }
    }

    public static void transferHeat(int row, int col, int[][] tempField, int rows, int cols, Particle[][] board) {
        if(row - 1 >= 0 && col - 1 >= 0 && row + 1 < rows && col + 1 < cols) {
            if (tempField[row][col] - tempField[row - 1][col - 1] > 2 && !board[row-1][col-1].getType().equals("Insulation")) {
                int transfer = ((tempField[row][col] - tempField[row - 1][col - 1]) / board[row][col].getTempValue());
                tempField[row][col] -= transfer;
                tempField[row - 1][col - 1] += transfer;
            }
            if (tempField[row][col] - tempField[row - 1][col] > 2 && !board[row-1][col].getType().equals("Insulation")) {
                int transfer = ((tempField[row][col] - tempField[row - 1][col]) / board[row][col].getTempValue());
                tempField[row][col] -= transfer;
                tempField[row - 1][col] += transfer;
            }
            if (tempField[row][col] - tempField[row - 1][col + 1] > 2 && !board[row-1][col+1].getType().equals("Insulation")) {
                int transfer = ((tempField[row][col] - tempField[row - 1][col + 1]) / board[row][col].getTempValue());
                tempField[row][col] -= transfer;
                tempField[row - 1][col + 1] += transfer;
            }
            if (tempField[row][col] - tempField[row][col - 1] > 2 && !board[row][col-1].getType().equals("Insulation")) {
                int transfer = ((tempField[row][col] - tempField[row][col - 1]) / board[row][col].getTempValue());
                tempField[row][col] -= transfer;
                tempField[row][col - 1] += transfer;
            }
            if (tempField[row][col] - tempField[row][col + 1] > 2 && !board[row][col+1].getType().equals("Insulation")) {
                int transfer = ((tempField[row][col] - tempField[row][col + 1]) / board[row][col].getTempValue());
                tempField[row][col] -= transfer;
                tempField[row][col + 1] += transfer;
            }
            if (tempField[row][col] - tempField[row + 1][col - 1] > 2 && !board[row+1][col-1].getType().equals("Insulation")) {
                int transfer = ((tempField[row][col] - tempField[row + 1][col - 1]) / board[row][col].getTempValue());
                tempField[row][col] -= transfer;
                tempField[row + 1][col - 1] += transfer;
            }
            if (tempField[row][col] - tempField[row + 1][col] > 2 && !board[row+1][col].getType().equals("Insulation")) {
                int transfer = ((tempField[row][col] - tempField[row + 1][col]) / board[row][col].getTempValue());
                tempField[row][col] -= transfer;
                tempField[row + 1][col] += transfer;
            }
            if (tempField[row][col] - tempField[row + 1][col + 1] > 2 && !board[row+1][col+1].getType().equals("Insulation")) {
                int transfer = ((tempField[row][col] - tempField[row + 1][col + 1]) / board[row][col].getTempValue());
                tempField[row][col] -= transfer;
                tempField[row + 1][col + 1] += transfer;
            }
        }
//        else if(col == 0 && (row != 0 || row != rows-1)) {
//
//        }
//  TODO edge cases


        else {
            if(tempField[row][col] < 0)
                tempField[row][col] += 10;
            else
                tempField[row][col] -= 10;
        }
    }


}
