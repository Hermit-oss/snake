package com.snakegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWindow extends JFrame implements KeyListener {
    private static final int BOARD_SIZE = 20;
    private static final int TILE_SIZE = 20;
    private static final int GAME_SPEED = 100;

    private List<Point> rocks;
    private List<Point> foodList;
    private List<Point> snake;
    private Direction snakeDirection;

    private BoardPanel boardPanel;

    public GameWindow() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        rocks = new ArrayList<>();
        foodList = new ArrayList<>();
        snake = new ArrayList<>();
        snakeDirection = Direction.RIGHT;

        generateRocks();
        generateFood();
        initializeSnake();

        boardPanel = new BoardPanel(rocks, foodList, snake, BOARD_SIZE);

        boardPanel.setPreferredSize(new Dimension(TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE));

        add(boardPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        boardPanel.setFocusable(true);
        boardPanel.requestFocus();
        boardPanel.addKeyListener(this);

        startGameLoop();
    }

    private void generateRocks() {
        Random random = new Random();
        int numRocks = random.nextInt(5) + 3;
        for (int i = 0; i < numRocks; i++) {
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);
            Point rock = new Point(x, y);
            if (!rocks.contains(rock) && !foodList.contains(rock) && !snake.contains(rock)) {
                rocks.add(rock);
            } else {
                i--; // Retry generating the rock
            }
        }
    }

    private void generateFood() {
        Random random = new Random();
        int numFood = 1; // Generate 1-3 food items
        for (int i = 0; i < numFood; i++) {
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);
            Point food = new Point(x, y);
            if (!rocks.contains(food) && !foodList.contains(food) && !snake.contains(food)) {
                foodList.add(food);
            } else {
                i--; // Retry generating the food
            }
        }
    }

    private void initializeSnake() {
        // Add the initial positions of the snake
        snake.add(new Point(6, 8));
        snake.add(new Point(7, 8));
        snake.add(new Point(8, 8));
        snake.add(new Point(9, 8));
    }

    private void startGameLoop() {
        Timer timer = new Timer(GAME_SPEED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSnake();
                checkCollision();
                boardPanel.repaint();
            }
        });
        timer.start();
    }

    private void updateSnake() {
        // Move the snake in the current direction
        Point head = new Point(snake.get(0));
        switch (snakeDirection) {
            case UP:
                head.y--;
                break;
            case DOWN:
                head.y++;
                break;
            case LEFT:
                head.x--;
                break;
            case RIGHT:
                head.x++;
                break;
        }

        // Insert the new head position at the front of the snake
        snake.add(0, head);

        // Remove the tail of the snake
        snake.remove(snake.size() - 1);
    }

    private void checkCollision() {
        // Check wall collision
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= BOARD_SIZE || head.y < 0 || head.y >= BOARD_SIZE) {
            gameOver();
            return;
        }

        // Check rock collision
        if (rocks.contains(head)) {
            gameOver();
            return;
        }

        // // Check self collision
        // if (snake.subList(1, snake.size()).contains(head)) {
        //     gameOver();
        //     return;
        // }

        // Check food collision
        for (Point food : foodList) {
            if (head.equals(food)) {
                // Remove the food and add a new one
                foodList.remove(food);
                generateFood();

                // Increase the snake's length
                snake.add(new Point());
                break;
            }
        }
    }

    private void gameOver() {
        // Game over logic goes here
        // For now, simply print "Game Over" and exit the program
        System.out.println("Game Over");
        System.exit(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Change the direction of the snake based on the arrow keys
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (snakeDirection != Direction.DOWN) {
                    snakeDirection = Direction.UP;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snakeDirection != Direction.UP) {
                    snakeDirection = Direction.DOWN;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (snakeDirection != Direction.RIGHT) {
                    snakeDirection = Direction.LEFT;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snakeDirection != Direction.LEFT) {
                    snakeDirection = Direction.RIGHT;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
