package org.example.entity;

import org.example.exception.InvalidGridException;

public class Grid {
    private final int rows;
    private final int columns;
    private final Cell[][] cells;

    public Grid(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new InvalidGridException("Grid dimensions should be greater than 0");
        }

        this.rows = rows;
        this.columns = columns;
        this.cells = new Cell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(false);
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

                if (inputGrid[row][col] == 1) this.cells[row][col].setAlive();
                else this.cells[row][col].setDead();
            }
        }
    }

    public int[][] deSerialize() {
        // DeSerialize the grid
        int[][] outputGrid = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                outputGrid[row][col] = this.cells[row][col].isAlive() ? 1 : 0;
            }
        }

        return outputGrid;
    }

    public void initializeRandomGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                boolean randomBoolean = Math.random() < 0.5;
                if (randomBoolean) {
                    this.cells[row][col].setAlive();
                } else {
                    this.cells[row][col].setDead();
                }
            }
        }
    }

    public boolean isSameGrid(Cell[][] cells) {
        if (cells.length != rows || cells[0].length != columns) {
            throw new InvalidGridException("Input grid dimensions do not match with grid size");
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (this.cells[row][col].isAlive() != cells[row][col].isAlive()) {
                    return false;
                }
            }
        }
        return true;
    }
}
