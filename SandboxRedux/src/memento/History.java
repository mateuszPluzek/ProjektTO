package memento;

import java.io.Serializable;
import java.util.ArrayList;

public class History implements Serializable {
    private ArrayList<Memento> savedHistory = new ArrayList<Memento>();

    public void takeMemento(Memento m) {
        savedHistory.add(m);
    }

    public Memento loadHistory(int index) {
        return savedHistory.get(index);
    }

    public ArrayList<Memento> getSavedHistory() {
        return savedHistory;
    }
}
