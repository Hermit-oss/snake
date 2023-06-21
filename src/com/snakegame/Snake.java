package com.snakegame;

import java.util.ArrayList;

/**
 * Represents the direction of movement.
 */
enum Direction {
    UP, DOWN, LEFT, RIGHT;

    /**
     * Checks if the direction is along the x-axis.
     *
     * @return true if the direction is along the x-axis, false otherwise.
     */
    public boolean isX() {
        return this == LEFT || this == RIGHT;
    }

    /**
     * Checks if the direction is along the y-axis.
     *
     * @return true if the direction is along the y-axis, false otherwise.
     */
    public boolean isY() {
        return this == UP || this == DOWN;
    }
}

/**
 * Represents the Snake in the game.
 */
class Snake {
    private Direction direction;
    private Point head;
    private ArrayList<Point> tail;

    /**
     * Creates a new Snake object with the specified coordinates.
     *
     * @param x the x-coordinate of the snake's head.
     * @param y the y-coordinate of the snake's head.
     */
    public Snake(int x, int y) {
        this.head = new Point(x, y);
        this.direction = Direction.RIGHT;
        this.tail = new ArrayList<Point>();

        this.tail.add(new Point(0, 0));
        this.tail.add(new Point(0, 0));
        this.tail.add(new Point(0, 0));
    }

    /**
     * Moves the snake in the current direction.
     */
    public void move() {
        ArrayList<Point> newTail = new ArrayList<Point>();

        for (int i = 0, size = tail.size(); i < size; i++) {
            Point previous = i == 0 ? head : tail.get(i - 1);

            newTail.add(new Point(previous.getX(), previous.getY()));
        }

        this.tail = newTail;

        this.head.move(this.direction, 10);
    }

    /**
     * Adds a new tail segment to the snake.
     */
    public void addTail() {
        this.tail.add(new Point(-10, -10));
    }

    /**
     * Turns the snake in the specified direction.
     *
     * @param d the direction to turn.
     */
    public void turn(Direction d) {
        if (d.isX() && direction.isY() || d.isY() && direction.isX()) {
            direction = d;
        }
    }

    /**
     * Returns the tail segments of the snake.
     *
     * @return the tail segments.
     */
    public ArrayList<Point> getTail() {
        return this.tail;
    }

    /**
     * Returns the head of the snake.
     *
     * @return the head.
     */
    public Point getHead() {
        return this.head;
    }
}
