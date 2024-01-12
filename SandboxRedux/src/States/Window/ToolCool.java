package States.Window;

import Window.Board;

public class ToolCool extends ButtonState {

    public ToolCool() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.incTemp(MouseY, MouseX, -80);
    }

}