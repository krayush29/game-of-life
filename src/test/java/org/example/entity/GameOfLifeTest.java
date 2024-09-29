package org.example.entity;

import org.example.exception.InvalidGridException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameOfLifeTest {

    @Test
    public void TestExceptionNegativeOrZeroForRowsAndCols() {
        assertThrows(InvalidGridException.class, () -> new GameOfLife(5, 0));

        assertThrows(InvalidGridException.class, () -> new GameOfLife(0, 5));

        assertThrows(InvalidGridException.class, () -> new GameOfLife(-1, 5));

        assertThrows(InvalidGridException.class, () -> new GameOfLife(5, -5));
    }

    @Test
    public void TestNoExceptionForValidInitializationOfGrid() {
        int[][] inputMatrix = new int[][]{
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 0}
        };

        GameOfLife gameOfLife = new GameOfLife(4, 4);
        assertDoesNotThrow(() -> gameOfLife.initialize(inputMatrix));
    }

    @Test
    public void TestExceptionForInValidInitializationOfGrid() {
        int[][] inputMatrix = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 0},
        };

        // Parsing 3x3 matrix to 4x4 grid
        GameOfLife gameOfLife = new GameOfLife(4, 4);
        assertThrows(InvalidGridException.class, () -> gameOfLife.initialize(inputMatrix));
    }
}