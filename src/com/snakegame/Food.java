package com.snakegame;

import java.awt.Color;

public abstract class Food {
    private Coordinate coordinate;
    private Color color;

    public Food(Coordinate coordinate, Color color) {
        this.coordinate = coordinate;
        this.color = color;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Color getColor() {
        return color;
    }

    public abstract void move();

    // Other common methods and behaviors for food

    public static class RedFood extends Food {
        public RedFood(Coordinate coordinate) {
            super(coordinate, Color.RED);
        }

        @Override
        public void move() {
            // Red food stays in place, so no movement logic needed
        }
    }

    public static class YellowFood extends Food {
        public YellowFood(Coordinate coordinate) {
            super(coordinate, Color.YELLOW);
        }

        @Override
        public void move() {
            // Yellow food moves in a chaotic manner, implement movement logic here
        }
    }

    public static class GreenFood extends Food {
        public GreenFood(Coordinate coordinate) {
            super(coordinate, Color.GREEN);
        }

        @Override
        public void move() {
            // Green food prefers to move in lines but turns from time to time, implement movement logic here
        }
    }

    public static class VioletFood extends Food {
        public VioletFood(Coordinate coordinate) {
            super(coordinate, Color.MAGENTA);
        }

        @Override
        public void move() {
            // Violet food runs away from the snake's head, implement movement logic here
        }
    }
}
