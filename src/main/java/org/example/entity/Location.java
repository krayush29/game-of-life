package org.example.entity;

public class Location {
    private final int row;
    private final int col;
    private final Cell cell;

    public Cell getCell() {
        return cell;
    }

    private final Location[] neighborLocations;

    public Location[] getNeighborLocations() {
        return neighborLocations;
    }

    public Location(int row, int col) {
        this.col = col;
        this.row = row;
        this.cell = new Cell(this);
        this.neighborLocations = new Location[8];
    }


    // Method to set neighbours for the current location
    public void setNeighbors(Grid grid) {
        int[][] directions = new int[][]{
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        int index = 0;

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (newRow >= 0 && newRow < grid.getRows() && newCol >= 0 && newCol < grid.getColumns()) {
                neighborLocations[index++] = grid.getLocations()[newRow][newCol];
            } else
                neighborLocations[index++] = null;
        }
    }
}

// Container => grid
