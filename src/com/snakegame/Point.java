package com.snakegame;

/**
 * Represents a point in a two-dimensional coordinate system.
 */
class Point {
    private int x;
    private int y;

    /**
     * Creates a new Point object with the specified coordinates.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new Point object with the coordinates copied from another point.
     *
     * @param p the point to copy coordinates from.
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Moves the point in the specified direction by the given value.
     *
     * @param d     the direction to move.
     * @param value the value to move by.
     */
    public void move(Direction d, int value) {
        switch (d) {
            case UP:
                this.y -= value;
                break;
            case DOWN:
                this.y += value;
                break;
            case RIGHT:
                this.x += value;
                break;
            case LEFT:
                this.x -= value;
                break;
        }
    }

    /**
     * Changes the position of the point
     *
     * @param dx change in position of the x coordinate.
     * @param dy change in position of the y cooridnate.
     */
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Returns the x-coordinate of the point.
     *
     * @return the x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the point.
     *
     * @return the y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param x the x-coordinate to set.
     * @return the modified Point object.
     */
    public Point setX(int x) {
        this.x = x;
        return this;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param y the y-coordinate to set.
     * @return the modified Point object.
     */
    public Point setY(int y) {
        this.y = y;
        return this;
    }

    /**
     * Checks if the point is equal to another point.
     *
     * @param p the point to compare.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point p) {
        return this.x == p.getX() && this.y == p.getY();
    }

    /**
     * Returns a string representation of the point in the format "(x, y)".
     *
     * @return the string representation of the point.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Checks if the point intersects with another point within a specified tolerance.
     *
     * @param p         the point to check for intersection.
     * @param tolerance the tolerance value for intersection.
     * @return true if the points intersect, false otherwise.
     */
    public boolean intersects(Point p, int tolerance) {
        int diffX = Math.abs(x - p.getX());
        int diffY = Math.abs(y - p.getY());

        return this.equals(p) || (diffX <= tolerance && diffY <= tolerance);
    }
}
