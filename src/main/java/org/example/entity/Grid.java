package org.example.entity;

import org.example.exception.InvalidGridException;

public class Grid {
    private final int rows;
    private final int columns;
    private final Location[][] locations;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Location[][] getLocations() {
        return locations;
    }

    public Grid(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new InvalidGridException("Grid dimensions should be greater than 0");
        }

        this.rows = rows;
        this.columns = columns;
        this.locations = new Location[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
               locations[i][j] = new Location(i, j);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                locations[i][j].setNeighbors(this);
            }
        }
    }

    public void serialize(int[][] inputGrid) {
        // Serialize the grid
        if (inputGrid.length != rows || inputGrid[0].length != columns) {
            throw new InvalidGridException("Input grid dimensions do not match with grid size");
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (inputGrid[row][col] != 0 && inputGrid[row][col] != 1) {
                    throw new InvalidGridException("Input grid should contain only 0s and 1s");
                }

                if (inputGrid[row][col] == 1) this.locations[row][col].getCell().setAlive();
                else this.locations[row][col].getCell().setDead();
            }
        }
    }

    public int[][] deSerialize() {
        // DeSerialize the grid
        int[][] outputGrid = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                outputGrid[row][col] = this.locations[row][col].getCell().isAlive() ? 1 : 0;
            }
        }

        return outputGrid;
    }

    public void initializeRandomGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                boolean randomBoolean = Math.random() < 0.5;
                if (randomBoolean) {
                    this.locations[row][col].getCell().setAlive();
                } else {
                    this.locations[row][col].getCell().setDead();
                }
            }
        }
    }

    public void nextGeneration() {
        boolean[][] nextState = new boolean[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Location location = locations[row][col];
                nextState[row][col] = location.getCell().computeNextState();
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                locations[row][col].getCell().setIsAlive(nextState[row][col]);
            }
        }
    }


    public boolean isSameGrid(Location[][] locations) {
        if (locations.length != rows || locations[0].length != columns) {
            throw new InvalidGridException("Input grid dimensions do not match with grid size");
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (this.locations[row][col].getCell().isAlive() != locations[row][col].getCell().isAlive()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Grid grid = (Grid) obj;
        return rows == grid.rows && columns == grid.columns && isSameGrid(grid.locations);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + rows;
        result = 31 * result + columns;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                result = 31 * result + (locations[row][col].getCell().isAlive() ? 1 : 0);
            }
        }
        return result;
    }

}
