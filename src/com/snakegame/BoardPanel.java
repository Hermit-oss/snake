package com.snakegame;

import java.awt.*;
import java.util.List;
import java.awt.Point;
import javax.swing.*;

public class BoardPanel extends JPanel {
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color SNAKE_COLOR = Color.GREEN;
    private static final Color FOOD_COLOR = Color.RED;
    private static final Color ROCK_COLOR = Color.GRAY;

    private List<Point> rocks;
    private List<Point> foodList;
    private List<Point> snake;
    private int boardSize;

    public BoardPanel(List<Point> rocks, List<Point> foodList, List<Point> snake, int boardSize) {
        this.rocks = rocks;
        this.foodList = foodList;
        this.snake = snake;
        this.boardSize = boardSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawRocks(g);
        drawFood(g);
        drawSnake(g);
    }

    private void drawRocks(Graphics g) {
        g.setColor(ROCK_COLOR);
        for (Point rock : rocks) {
            int x = rock.x * getWidth() / boardSize;
            int y = rock.y * getHeight() / boardSize;
            g.fillRect(x, y, getWidth() / boardSize, getHeight() / boardSize);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(FOOD_COLOR);
        for (Point food : foodList) {
            int x = food.x * getWidth() / boardSize;
            int y = food.y * getHeight() / boardSize;
            g.fillOval(x, y, getWidth() / boardSize, getHeight() / boardSize);
        }
    }

    private void drawSnake(Graphics g) {
        g.setColor(SNAKE_COLOR);
        for (Point snakePart : snake) {
            int x = snakePart.x * getWidth() / boardSize;
            int y = snakePart.y * getHeight() / boardSize;
            g.fillRect(x, y, getWidth() / boardSize, getHeight() / boardSize);
        }
    }
}