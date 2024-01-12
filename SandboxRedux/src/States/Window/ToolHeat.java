package States.Window;

import Window.Board;

public class ToolHeat extends ButtonState {

    public ToolHeat() {
        super();
    }
    public void draw(Board board, int MouseY, int MouseX) {
        board.incTemp(MouseY, MouseX, 80);
    }

}
