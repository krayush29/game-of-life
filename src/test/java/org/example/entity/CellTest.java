package org.example.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CellTest {
    @Test
    public void testCellDiesWhenLiveCellWithFewerThanTwoLiveNeighbours() {
        Cell cell = new Cell(true);
        Cell nextState = cell.nextState(1);

        assertFalse(nextState.isAlive());
    }

    @Test
    public void testCellLivesWhenLiveCellWithTwoOrThreeLiveNeighbours() {
        Cell cell = new Cell(true);
        Cell nextStateWithTwoNeighbours = cell.nextState(2);
        Cell nextStateWithThreeNeighbours = cell.nextState(3);

        assertTrue(nextStateWithTwoNeighbours.isAlive());
        assertTrue(nextStateWithThreeNeighbours.isAlive());
    }

    @Test
    public void testCellDiesWhenLiveCellWithMoreThanThreeLiveNeighbours() {
        Cell cell = new Cell(true);
        Cell nextState = cell.nextState(4);

        assertFalse(nextState.isAlive());
    }

    @Test
    public void testCellLivesWhenDeadCellWithExactlyThreeLiveNeighbours() {
        Cell cell = new Cell(false);
        Cell nextState = cell.nextState(3);

        assertTrue(nextState.isAlive());
    }

    @Test
    public void testCellStaysDeadWhenDeadCellWithFewerOrMoreThanThreeLiveNeighbours() {
        Cell cell = new Cell(false);
        Cell nextStateWithTwoNeighbours = cell.nextState(2);
        Cell nextStateWithFourNeighbours = cell.nextState(4);

        assertFalse(nextStateWithTwoNeighbours.isAlive());
        assertFalse(nextStateWithFourNeighbours.isAlive());
    }
}