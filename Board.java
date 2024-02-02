package org.cis1200.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * GameCourt
 *
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */
public class Board extends JPanel {

    // the state of the game logic
    private Snake snake; // the snake, keyboard control
    private Apple apple; // the apple, changes position each time snake eats

    private boolean moveRight = false, moveLeft = false, moveUp = false, moveDown = false;
    private boolean playing;

    private final JLabel status; // Current status text, i.e. "Running..."

    // Game constants
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 360;
    public static final int VELOCITY = 20;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 80;

    public Board(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // The timer is an object which triggers an action periodically with the
        // given INTERVAL. We register an ActionListener with this timer, whose
        // actionPerformed() method is called each time the timer triggers. We
        // define a helper method called tick() that actually does everything
        // that should be done in a single time step.
        Timer timer = new Timer(INTERVAL, e -> tick());
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // This key listener allows the square to move as long as an arrow key
        // is pressed, by changing the square's velocity accordingly. (The tick
        // method below actually moves the square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT && !moveRight) {
                    snake.setVx(-VELOCITY);
                    snake.setVy(0);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !moveLeft) {
                    snake.setVx(VELOCITY);
                    snake.setVy(0);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && !moveUp) {
                    snake.setVy(VELOCITY);
                    snake.setVx(0);
                } else if (e.getKeyCode() == KeyEvent.VK_UP && !moveDown) {
                    snake.setVy(-VELOCITY);
                    snake.setVx(0);
                }
            }
        });

        this.status = status;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        snake = new Snake(BOARD_WIDTH, BOARD_HEIGHT, 200, 160);
        apple = new Apple(BOARD_WIDTH, BOARD_HEIGHT, snake, 160, 160);

        repaint();

        playing = true;
        moveUp = false;
        moveDown = false;
        moveLeft = false;
        moveRight = false;
        status.setText("Running...");

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */
    void tick() {
        if (playing) {
            if (snake.willHitWall() || snake.hitSelf()) {
                playing = false;
                status.setText("You lose!");
            } else if (snake.willIntersect(apple)) {
                snake.addSquare();
                snake.move();
                if (snake.gameWon()) {
                    playing = false;
                    status.setText("You win!");
                } else {
                    apple.spawn();
                }
            } else {
                snake.move();
            }

            if (snake.getVx() != 0) {
                moveRight = true;
                moveLeft = true;
                moveUp = false;
                moveDown = false;
            }
            if (snake.getVy() != 0) {
                moveRight = false;
                moveLeft = false;
                moveUp = true;
                moveDown = true;
            }

            // update the display
            repaint();
        }
    }

    public void loadGame(String filePath) {
        try {
            BufferedReader br = FileLineIterator.fileToReader(filePath);
            String[] snakeCoords = br.readLine().split(", ");

            snake = new Snake(BOARD_WIDTH, BOARD_HEIGHT, 0, 0);
            snake.px = (Integer.parseInt(snakeCoords[0]));
            snake.py = (Integer.parseInt(snakeCoords[1]));
            snake.setVx(Integer.parseInt(snakeCoords[2]));
            snake.setVy(Integer.parseInt(snakeCoords[3]));

            int snakeSize = Integer.parseInt(br.readLine());
            for (int i = 0; i < snakeSize; i++) {
                snake.addSquare();
            }

            String[] appleCoords = br.readLine().split(", ");
            apple.setPx(Integer.parseInt(appleCoords[0]));
            apple.setPy(Integer.parseInt(appleCoords[1]));

            for (int i = 0; i < snakeSize; i++) {
                String[] squareCoords = br.readLine().split(", ");
                snake.getSquare(i).setPx(Integer.parseInt(squareCoords[0]));
                snake.getSquare(i).setPy(Integer.parseInt(squareCoords[1]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        repaint();

        if (snake.getVx() > 0) {
            moveRight = true;
        }
        if (snake.getVy() < 0) {
            moveUp = true;
        }
        if (snake.getVx() < 0) {
            moveLeft = true;
        }
        if (snake.getVy() > 0) {
            moveDown = true;
        }
        snake.setVx(0);
        snake.setVy(0);

        playing = true;

        status.setText("Running...");


        requestFocusInWindow();

    }
    public void saveGame(String filePath) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(Integer.toString(snake.px) + ", " + Integer.toString(snake.py) + ", ");
            bw.write(Integer.toString(snake.getVx()) + ", " + Integer.toString(snake.getVy()));
            bw.newLine();
            bw.write(Integer.toString(snake.getBodyLength()));
            bw.newLine();
            bw.write(Integer.toString(apple.getPx()) + ", " + Integer.toString(apple.getPy()));
            bw.newLine();
            for (int i = 0; i < snake.getBodyLength(); i++) {
                Square s = snake.getSquare(i);
                bw.write(Integer.toString(s.getPx()) + ", " + Integer.toString(s.getPy()));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        playing = false;
        moveUp = false;
        moveDown = false;
        moveRight = false;
        moveLeft = false;

        status.setText("Paused");

        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        apple.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}