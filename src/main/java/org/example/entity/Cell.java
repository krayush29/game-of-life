package org.example.entity;

public class Cell {
    private boolean isAlive;
    private Location location;

    public Cell(Location location) {
        this.isAlive = false;
        this.location = location;
    }

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive() {
        this.isAlive = true;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
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

    public int countAliveNeighbours() {
        int aliveCount = 0;

        for (Location neighbour : location.getNeighborLocations()) {
            if (neighbour != null && neighbour.getCell().isAlive()) {
                aliveCount++;
            }
        }
        return aliveCount;
    }

    public boolean computeNextState() {
        int aliveNeighbours = countAliveNeighbours();

        if(isAlive){
            return aliveNeighbours >= 2 && aliveNeighbours <= 3;
        }
        else{
            return aliveNeighbours == 3;
        }
    }
}
