package org.example.entity;

public class Cell {
    private boolean isAlive;

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive() {
        this.isAlive = true;
    }

    public void setDead() {
        this.isAlive = false;
    }

    public Cell nextState(int aliveNeighbours) {
        if (this.isAlive) {
            return (aliveNeighbours < 2 || aliveNeighbours > 3) ? new Cell(false) : new Cell(true);
        } else {
            return (aliveNeighbours == 3) ? new Cell(true) : new Cell(false);
        }
    }
}
