package com.snakegame;

import java.util.*;
import java.util.List;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Represents the status of the game.
 */
enum GameStatus {
    NOT_STARTED, RUNNING, PAUSED, GAME_OVER
}

/**
 * The main game panel for Snake.
 */
class Game extends JPanel {
    private Timer timer;
    private Snake snake;
    private Rock rock;
    private Food food;
    private List<Food> foods;
    private int points = 0;
    private int best = 0;
    private GameStatus status;

    private static final Font FONT_M = new Font("MV Boli", Font.PLAIN, 24);
    private static final Font FONT_M_ITALIC = new Font("MV Boli", Font.ITALIC, 24);
    private static final Font FONT_L = new Font("MV Boli", Font.PLAIN, 84);
    private static final Font FONT_XL = new Font("MV Boli", Font.PLAIN, 150);
    
    public static final int BORDER = 40;
    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;

    private static final int DELAY = 50;

    /**
     * Creates a new Game panel.
     */
    public Game() {
        addKeyListener(new GameKeyListener());
        setFocusable(true);
        setBackground(new Color(130, 205, 71));
        setDoubleBuffered(true);

        // Initialize the list of foods and create a new food instance
        foods = new ArrayList<>();
        food = new Food(WIDTH, HEIGHT, BORDER);
        foods.add(food);

        // Create the rock and snake instances
        rock = new Rock(15, WIDTH, HEIGHT, BORDER);
        snake = new Snake(WIDTH / 2, HEIGHT / 2);

        // Set the initial game status
        status = GameStatus.NOT_STARTED;

        // Repaint the panel
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Render the game components
        render(g);
        rock.draw((Graphics2D) g);
        food.draw((Graphics2D) g);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Updates the game state.
     */
    private void update() {
        // Move the snake
        snake.move();

        // Update the behavior of each food
        for (Food food : foods) {
            int foodXPos = food.getPosition().getX();
            int foodYPos = food.getPosition().getY();
            int playerXPos = snake.getHead().getX();
            int playerYPos = snake.getHead().getY();
            int dx = 0, dy = 0;
            int[][] options = { // default, xright, xleft, ydown, yup
                {0, 0, /* */ 10, 0, /* */ -10, 0, /* */ 0, 10, /* */ 0, -10}, // 0 default
                {0, 0, /* */ 10, 0, /* */  10, 0, /* */ 0, 10, /* */ 0, -10}, // 1
                {0, 0, /* */ 10, 0, /* */ -10, 0, /* */ 0, 10, /* */ 0,  10}, // 2
                {0, 0, /* */ 10, 0, /* */  10, 0, /* */ 0, 10, /* */ 0,  10}, // 3
                {0, 0, /* */-10, 0, /* */ -10, 0, /* */ 0, 10, /* */ 0, -10}, // 4
                {0, 0, /* */ 10, 0, /* */ -10, 0, /* */ 0,-10, /* */ 0, -10}, // 5
                {0, 0, /* */-10, 0, /* */ -10, 0, /* */ 0,-10, /* */ 0, -10}, // 6
                {0, 0, /* */-10, 0, /* */ -10, 0, /* */ 0, 10, /* */ 0,  10}, // 7 upper-right
                {0, 0, /* */ 10, 0, /* */  10, 0, /* */ 0,-10, /* */ 0, -10}  // 8 lower-left
            };
            switch (food.getType()) {
                case RED:
                    // Red food behavior (stays in one place)
                    break;
                case YELLOW:
                    // Yellow food behavior (moves chaotically)
                    int validPosYellow = checkFoodPosition(foodXPos, foodYPos);
                    int option = (int) (Math.random() * 4);
                    dx = options[validPosYellow][option * 2];
                    dy = options[validPosYellow][option * 2 + 1];
                    break;
                case MAGENTA:
                    // Purple food behavior (runs away from the player)
                    int validPosMagenta = checkFoodPosition(foodXPos, foodYPos);
                    int distanceX = foodXPos - playerXPos;
                    int distanceY = foodYPos - playerYPos;
                    
                    if (distanceX < 0 && validPosMagenta != 1 && validPosMagenta != 3 && validPosMagenta != 8) {
                        dx = -10; // Move to the left
                    } else if (distanceX > 0 && validPosMagenta != 4 && validPosMagenta != 6 && validPosMagenta != 7) {
                        dx = 10; // Move to the right
                    }
                    
                    if (distanceY < 0 && validPosMagenta != 2 && validPosMagenta != 3 && validPosMagenta != 7) {
                        dy = -10; // Move up
                    } else if (distanceY > 0 && validPosMagenta != 5 && validPosMagenta != 6 && validPosMagenta != 8) {
                        dy = 10; // Move down
                    }
                    break;
            }
            food.getPosition().move(dx, dy);

            // Check if the snake has collided with the food
            if (food.getPosition().intersects(snake.getHead(), food.getSize() - 1)) {
                snake.addTail();
                food.respawn(WIDTH, HEIGHT, BORDER); // Respawn the food
                points++;
            }

            // Check if the game is over
            checkForGameOver();
        }
    }

    private int checkFoodPosition(int pos_x, int pos_y) {
        if (pos_x <= BORDER && pos_y <= BORDER) { // Out of bounds upper-left
            return 3;
        }
        if (pos_x >= WIDTH + BORDER - food.getSize() && pos_y <= BORDER) { // Out of bounds upper-right
            return 7;
        }
        if (pos_x <= BORDER && pos_y >= HEIGHT + BORDER - food.getSize()) { // Out of bounds lower-left
            return 8;
        }
        if (pos_x >= WIDTH + BORDER - food.getSize() && pos_y >= HEIGHT + BORDER - food.getSize()) { // Out of bounds lower-right
            return 6;
        }
        if (pos_x <= BORDER) { // Out of bounds to the left
            return 1;
        }
        if (pos_y <= BORDER) { // Out of bounds up
            return 2;
        }
        if (pos_x >= WIDTH + BORDER - food.getSize()) { // Out of bounds to the right
            return 4;
        }
        if (pos_y >= HEIGHT + BORDER - food.getSize()) { // Out of bounds down
            return 5;
        }
        return 0; // In map boundaries
    }

    /**
     * Resets the game to its initial state.
     */
    private void reset() {
        points = 0;
        food.respawn(WIDTH, HEIGHT, BORDER);
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        setStatus(GameStatus.RUNNING);
    }

    /**
     * Sets the game status to the specified value and performs necessary actions.
     *
     * @param newStatus the new game status to set.
     */
    private void setStatus(GameStatus newStatus) {
        switch (newStatus) {
            case NOT_STARTED:
                // TODO: Handle not started case
                break;
            case RUNNING:
                timer = new Timer();
                timer.schedule(new GameLoop(), 0, DELAY);
                break;
            case PAUSED:
                // TODO: Handle pause mechanic
                break;
            case GAME_OVER:
                timer.cancel();
                best = points > best ? points : best;
                break;
        }

        status = newStatus;
    }

    /**
     * Toggles the game between paused and running states.
     */
    private void togglePause() {
        setStatus(status == GameStatus.PAUSED ? GameStatus.RUNNING : GameStatus.PAUSED);
    }

    /**
     * Checks if the game is over due to hitting the wall or itself.
     */
    private void checkForGameOver() {
        Point head = snake.getHead();
        boolean hitBoundary = head.getX() <= BORDER - 5
                || head.getX() >= WIDTH + BORDER - 5
                || head.getY() <= BORDER - 5
                || head.getY() >= HEIGHT + BORDER - 5;

        boolean ateItself = false;
        boolean hitRock = rock.collidesWith(head);

        for (Point t : snake.getTail()) {
            ateItself = ateItself || head.equals(t);
        }

        if (hitBoundary || ateItself || hitRock) {
            setStatus(GameStatus.GAME_OVER);
        }
    }

    /**
     * Draws a string centered horizontally on the screen.
     *
     * @param g     the graphics object to draw on.
     * @param text  the text to draw.
     * @param font  the font to use for drawing.
     * @param y     the y-coordinate of the text.
     */
    public void drawCenteredString(Graphics g, String text, Font font, int y) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (WIDTH - metrics.stringWidth(text)) / 2;

        g.setFont(font);
        g.drawString(text, x, y);
    }

    /**
     * Renders the game on the screen.
     *
     * @param g the graphics object to draw on.
     */
    private void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.setFont(FONT_M);

        if (status == GameStatus.NOT_STARTED) {
            drawCenteredString(g2d, "SNAKE", FONT_XL, 200);
            drawCenteredString(g2d, "GAME", FONT_XL, 300);
            drawCenteredString(g2d, "Press any key to begin", FONT_M_ITALIC, 330);
            return;
        }

        Point p = snake.getHead();

        g2d.drawString("SCORE: " + String.format("%02d", points), 20, 30);
        g2d.drawString("BEST: " + String.format("%02d", best), 630, 30);

        for (Food food : foods) {
            g2d.fillRect(food.getPosition().getX(), food.getPosition().getY(), food.getSize(), food.getSize());
        }

        if (status == GameStatus.GAME_OVER) {
            drawCenteredString(g2d, "Press enter to start again", FONT_M_ITALIC, 330);
            drawCenteredString(g2d, "GAME OVER", FONT_L, 300);
        }

        if (status == GameStatus.PAUSED) {
            g2d.drawString("Paused", 600, 14);
        }

        g2d.setColor(new Color(33, 70, 199));
        g2d.fillRect(p.getX(), p.getY(), 10, 10);

        for (int i = 0, size = snake.getTail().size(); i < size; i++) {
            Point t = snake.getTail().get(i);
            g2d.fillRect(t.getX(), t.getY(), 10, 10);
        }

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawRect(BORDER, BORDER, WIDTH, HEIGHT);
    }

    /**
     * The key listener for handling user input.
     */
    private class GameKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (status == GameStatus.RUNNING) {
                switch (key) {
                    case KeyEvent.VK_LEFT:
                        snake.turn(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.turn(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        snake.turn(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        snake.turn(Direction.DOWN);
                        break;
                }
            }

            if (status == GameStatus.NOT_STARTED) {
                setStatus(GameStatus.RUNNING);
            }

            if (status == GameStatus.GAME_OVER && key == KeyEvent.VK_ENTER) {
                reset();
            }

            if (key == KeyEvent.VK_P) {
                togglePause();
            }
        }
    }

    /**
     * The game loop for updating the game state and repainting the panel.
     */
    private class GameLoop extends TimerTask {
        public void run() {
            update();
            repaint();
        }
    }
}
