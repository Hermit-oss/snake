package com.snakegame;

import java.awt.*;
import javax.swing.*;

/**
 * The Main class is responsible for starting the Snake game.
 * It creates the main window and initializes the game.
 */
public class Main extends JFrame {
    public Main() {
        initUI();
    }

    /**
     * Initializes the user interface of the game.
     */
    private void initUI() {
        add(new Game());

        setTitle("Snake");
        setSize(800, 800);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * The entry point of the program.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main ex = new Main();
            ex.setVisible(true);
        });
    }
}
