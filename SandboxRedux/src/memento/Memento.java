package memento;

import Window.Board;

import java.io.Serializable;

public class Memento implements Serializable {
    private Board backup;
    private Board sim;

    public Memento(Board sim) {
        this.sim = sim;
        this.backup = sim.backup();
    }

    public void restore() {
        sim.restore(backup);
    }
}
