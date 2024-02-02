package org.cis1200.snake;

// imports necessary libraries for Java swing

import javax.swing.*;
import java.awt.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class RunSnake implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for
        // local variables.

        // Top-level frame in which game components live.
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("SNAKE GAME");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final Board board = new Board(status);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        final JButton save = new JButton("Save");
        save.addActionListener(e -> board.saveGame("files/game_state.txt"));
        control_panel.add(save);

        final JButton load = new JButton("Load");
        load.addActionListener(e -> board.loadGame("files/game_state.txt"));
        control_panel.add(load);

        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(e -> {
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Snake Game.\n " +
                    "Use the arrow keys to move the snake in the respective directions.\n" +
                    "Eat apples to grow the length of the snake.\n" +
                    "You win if your snake covers the whole board.\n" +
                    "You lose if the snake hits a wall or itself.\n" +
                    "Hit reset to restore original game state.\n" +
                    "Hit save to pause the game and save the current state.\n" +
                    "Hit load to start the game again and load the last saved state.\n" +
                    "Press any arrow key to start playing");
            board.reset();
        });
        control_panel.add(instructions);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        board.reset();
    }
}