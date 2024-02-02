package org.cis1200.snake;

import java.awt.*;
import java.util.LinkedList;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a circle of a specified color.
 */
public class Apple extends BoardObj {
    public static final int SIZE = 20;

    private int boardWidth, boardHeight;

    private Snake snake;

    public Apple(int boardWidth, int boardHeight, Snake snake, int px, int py) {
        super(px, py, SIZE, SIZE, boardWidth, boardHeight);
        this.snake = snake;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

    }

    public void spawn() {
        LinkedList<Integer> xCoords = new LinkedList<>();
        LinkedList<Integer> yCoords = new LinkedList<>();
        for (int i = 0; i < boardWidth - SIZE; i += SIZE) {
            for (int j = 0; j < boardHeight - SIZE; j += SIZE) {
                boolean hasSnake = false;
                for (int k = 0; k < snake.getBodyLength(); k++) {
                    Square s = snake.getSquare(k);
                    if (i == s.getPx() && j == s.getPy()) {
                        hasSnake = true;
                    }
                }
                if (!hasSnake && snake.getPx() != i && snake.getPy() != j) {
                    xCoords.add(i);
                    yCoords.add(j);
                }
            }
        }
        int rand = (int) (Math.random() * xCoords.size());
        this.setPx(xCoords.get(rand));
        this.setPy(yCoords.get(rand));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}