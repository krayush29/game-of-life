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

    public void nextGeneration() {
        Cell[][] nextGeneration = new Cell[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int aliveNeighbours = countLiveNeighbours(row, col);
                nextGeneration[row][col] = cells[row][col].nextState(aliveNeighbours);
            }
        }

        for (int row = 0; row < rows; row++) {
            System.arraycopy(nextGeneration[row], 0, cells[row], 0, columns);
        }
    }


    private int countLiveNeighbours(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < columns && !(i == row && j == col)) {
                    if (cells[i][j].isAlive()) {
                        count++;
                    }
                }
            }
        }
        return count;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Grid grid = (Grid) obj;
        return rows == grid.rows && columns == grid.columns && isSameGrid(grid.cells);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + rows;
        result = 31 * result + columns;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                result = 31 * result + (cells[row][col].isAlive() ? 1 : 0);
            }
        }
        return result;
    }
}
