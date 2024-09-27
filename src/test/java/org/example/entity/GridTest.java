package org.example.entity;

import org.example.exception.InvalidGridException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GridTest {

    // Test initialize the grid with zero or negative rows and columns
    @Test
    public void testExceptionForInitializingGridWithZeroOrNegativeRowsAndColumns() {
        // Initialize row and columns with Zeros
        assertThrows(InvalidGridException.class, () -> new Grid(0, 5));
        assertThrows(InvalidGridException.class, () -> new Grid(5, 0));

        // Initialize row and columns with Negative values
        assertThrows(InvalidGridException.class, () -> new Grid(5, -1));
        assertThrows(InvalidGridException.class, () -> new Grid(-1, 5));
    }

    // Test serialize the Integer grid to Cell Grid
    @Test
    public void testSerializeIntegerGridToCellGrid() {
        Grid grid = new Grid(3, 3);
        int[][] inputMatrix = new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        };

        grid.serialize(inputMatrix);

        Cell[][] expectedDeSerializedGGrid = new Cell[][]{
                {new Cell(true), new Cell(false), new Cell(true)},
                {new Cell(false), new Cell(true), new Cell(false)},
                {new Cell(true), new Cell(false), new Cell(true)}
        };

        assertTrue(grid.isSameGrid(expectedDeSerializedGGrid));
    }

    // Test serialize and deserialize the Integer grid to get the same Integer grid
    @Test
    public void testSerializeAndDeserializeToGetSameIntegerGrid() {
        Grid grid = new Grid(3, 3);
        int[][] inputMatrix = new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        };

        grid.serialize(inputMatrix);
        int[][] expectedCells = grid.deSerialize();

        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[0].length; j++) {
                assertEquals(inputMatrix[i][j], expectedCells[i][j]);
            }
        }
    }

    @Test
    public void testExceptionWhenSerializeGridOfDifferentDimension() {
        Grid grid = new Grid(3, 3);
        int[][] inputMatrix = new int[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 0},
                {1, 0, 1, 0}
        };

        // Grid Dimensions 3x3 and input grid dimensions 3x4
        assertThrows(InvalidGridException.class, () -> grid.serialize(inputMatrix));
    }

    @Test
    public void testExceptionWhenSerializedGirdHasValueOtherThanZeroOrOne() {
        Grid grid = new Grid(3, 3);
        int[][] inputMatrix = new int[][]{
                {1, 0, 1},
                {0, 4, 0},
                {1, 0, 8}
        };

        // serialized grid has value other than 0 and 1
        assertThrows(InvalidGridException.class, () -> grid.serialize(inputMatrix));
    }

    @Test
    public void testInitializeGirdWithRandomCellValues() {
        Grid grid = new Grid(3, 3);

        assertDoesNotThrow(grid::initializeRandomGrid);
    }

    @Test
    public void testNextGenerationGridByStep1() {
        Grid inputGrid = new Grid(3, 3);
        int[][] inputMatrix = new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        };

        inputGrid.serialize(inputMatrix);
        inputGrid.nextGeneration();

        int[][] expectedCells = new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        Grid expectedGrid = new Grid(3, 3);
        expectedGrid.serialize(expectedCells);

        assertEquals(inputGrid, expectedGrid);
    }

    @Test
    public void testNextGenerationGridByStep2() {
        Grid inputGrid = new Grid(4, 4);
        int[][] inputMatrix = new int[][]{
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 0}
        };

        inputGrid.serialize(inputMatrix);
        inputGrid.nextGeneration();
        inputGrid.nextGeneration();

        int[][] expectedCells = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0}
        };

        Grid expectedGrid = new Grid(4, 4);
        expectedGrid.serialize(expectedCells);

        assertEquals(inputGrid, expectedGrid);
    }
}
