package org.cis1200.snake;

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It
 * is displayed as a square of a specified color.
 */
public class Square extends BoardObj {
    public static final int SIZE = 20;
    public static final int INIT_POS_X = 0;
    public static final int INIT_POS_Y = 0;


    /**
     * Note that, because we don't need to do anything special when constructing
     * a Square, we simply use the superclass constructor called with the
     * correct parameters.
     */
    public Square(int courtWidth, int courtHeight) {
        super(INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);

    }

    /**
     * Determine whether the game object will hit a wall in the next time step.
     * If so, return true.
     *
     * @return ture if hit wall, false if all clear.
     */

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}