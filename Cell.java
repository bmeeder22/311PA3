import java.awt.*;

public class Cell {
    int score;
    int parent;

    Cell(int score, int parent) {
        this.score = score;
        this.parent = parent;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int newParent) {
        parent = newParent;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
