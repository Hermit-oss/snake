package com.snakegame;

import java.awt.*;
import java.util.Random;

enum FoodType {
    RED, YELLOW, MAGENTA
}

/**
 * The Food class represents the food item in the game.
 * It has a position, size, color, and type.
 * The food can be of three types: RED, YELLOW, or MAGENTA.
 * The position and type of the food are randomly generated.
 */
class Food {
    private Point position;
    private static final int FOOD_SIZE = 10;
    private Color color;
    private FoodType type;

    /**
     * Constructs a new Food object with a random position and type.
     *
     * @param mapWidth  the width of the game map.
     * @param mapHeight the height of the game map.
     */
    public Food(int mapWidth, int mapHeight, int border) {
        position = generateRandomPosition(mapWidth, mapHeight, border);
        type = generateRandomFoodType();
        setColor();
    }

    /**
     * Generates a random position within the game map.
     *
     * @param mapWidth  the width of the game map.
     * @param mapHeight the height of the game map.
     * @return a randomly generated Point representing the position.
     */
    private Point generateRandomPosition(int mapWidth, int mapHeight, int border) {
        int x = ((int) (mapWidth * Math.random() + border - FOOD_SIZE) / 10) * 10;
        int y = ((int) (mapHeight * Math.random() + border - FOOD_SIZE) / 10) * 10;
        return new Point(x, y);
    }

    /**
     * Sets the color of the food based on its type.
     */
    private void setColor() {
        if (type == FoodType.RED) {
            color = Color.RED;
        } else if (type == FoodType.YELLOW) {
            color = Color.YELLOW;
        } else if (type == FoodType.MAGENTA) {
            color = Color.MAGENTA;
        } else {
            color = Color.WHITE;
        }
    }

    /**
     * Generates a random type for the food.
     *
     * @return a randomly selected FoodType.
     */
    private FoodType generateRandomFoodType() {
        int randomIndex = new Random().nextInt(FoodType.values().length);
        return FoodType.values()[randomIndex];
    }

    /**
     * Respawns the food at a random position and with a random type.
     *
     * @param mapWidth  the width of the game map.
     * @param mapHeight the height of the game map.
     */
    public void respawn(int mapWidth, int mapHeight, int border) {
        type = generateRandomFoodType();
        position = generateRandomPosition(mapWidth, mapHeight, border);
        setColor();
    }

    /**
     * Draws the food on the graphics object.
     *
     * @param g2d the Graphics2D object to draw on.
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(position.getX(), position.getY(), FOOD_SIZE, FOOD_SIZE);
    }

    /**
     * Returns the position of the food.
     *
     * @return the position of the food as a Point.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Returns the size of the food.
     *
     * @return the size of the food.
     */
    public int getSize() {
        return FOOD_SIZE;
    }

    /**
     * Returns the type of the food.
     *
     * @return the type of the food as a FoodType.
     */
    public FoodType getType() {
        return type;
    }
}
