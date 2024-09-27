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
}
