package com.snakegame;

import java.awt.*;

/**
 * The Rock class represents a rock object in the game.
 * Rocks are obstacles that the snake must avoid.
 * They have a position and size.
 */
class Rock {
    private Point[] rocks;
    private static final int ROCK_SIZE = 10;

    /**
     * Constructs a new Rock object with the specified number of rocks and their positions.
     *
     * @param numRocks  the number of rocks to generate.
     * @param mapWidth  the width of the game map.
     * @param mapHeight the height of the game map.
     */
    public Rock(int numRocks, int mapWidth, int mapHeight) {
        rocks = new Point[numRocks];
        generateRocks(numRocks, mapWidth, mapHeight);
    }

    /**
     * Generates the positions for the rocks.
     *
     * @param numRocks  the number of rocks to generate.
     * @param mapWidth  the width of the game map.
     * @param mapHeight the height of the game map.
     */
    private void generateRocks(int numRocks, int mapWidth, int mapHeight) {
        for (int i = 0; i < numRocks; i++) {
            int x = (int) (Math.random() * (mapWidth - ROCK_SIZE));
            int y = (int) (Math.random() * (mapHeight - ROCK_SIZE));
            rocks[i] = new Point(x, y);
        }
    }

    /**
     * Draws the rocks on the graphics object.
     *
     * @param g2d the Graphics2D object to draw on.
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        for (Point rock : rocks) {
            g2d.fillRect(rock.getX(), rock.getY(), ROCK_SIZE, ROCK_SIZE);
        }
    }

    /**
     * Checks if a point collides with any of the rocks.
     *
     * @param point the point to check for collision.
     * @return true if the point collides with any rock, false otherwise.
     */
    public boolean collidesWith(Point point) {
        for (Point rock : rocks) {
            if (point.intersects(rock, ROCK_SIZE)) {
                return true;
            }
        }
        return false;
    }
}
