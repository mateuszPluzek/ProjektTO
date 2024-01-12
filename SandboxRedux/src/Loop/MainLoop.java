package Loop;

import Window.*;

public class MainLoop {
    private static MainLoop loop;
    private int framesPerSecond = 20;
    private Window screen;
    private Board board;

    private MainLoop(String title, int width, int height, int gridSize) {
        this.board = new Board(height, width, gridSize);
        this.screen = new Window(title, width, height, board);
    }

    public void run() {
        do {
            try {
                Thread.sleep((long)(1000 / this.framesPerSecond));
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }




            this.board.cycle();
            this.screen.getFrame().repaint();
        } while(true);

    }

    public static MainLoop getInstance() {
        if (loop == null) {
            loop = new MainLoop("Sandbox", 1000, 1000, 10);
        }

        return loop;
    }
}