package org.cis1200.snake;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class SnakeTest {

    @Test
    public void testEatApple() {
        Snake snake = new Snake(400, 360, 0,0);
        Apple apple = new Apple(400, 360, snake, 20, 0);

        snake.setVx(20);
        snake.setVy(0);

        if (snake.willIntersect(apple)) {
            snake.addSquare();
            snake.move();
            apple.spawn();
        }
        assertEquals(1, snake.getBodyLength());
        assertNotEquals(20, apple.getPx());
        assertNotEquals(0, apple.getPy());
    }

    @Test
    public void testHitWallRight() {
        Snake snake = new Snake(40, 20, 0,0);
        snake.setVx(20);
        snake.move();

        assertTrue(snake.willHitWall());
    }

    @Test
    public void testHitWallLeft() {
        Snake snake = new Snake(40, 20, 20,0);
        snake.setVx(-20);
        snake.move();

        assertTrue(snake.willHitWall());
    }

    @Test
    public void testHitWallUp() {
        Snake snake = new Snake(20, 40, 0,20);
        snake.setVy(-20);
        snake.move();

        assertTrue(snake.willHitWall());
    }

    @Test
    public void testHitWallDown() {
        Snake snake = new Snake(20, 40, 0,0);
        snake.setVy(20);
        snake.move();

        assertTrue(snake.willHitWall());
    }

    @Test
    public void testHitSelf() {
        Snake snake = new Snake(400, 360, 200, 160);

        snake.setVx(20);
        snake.setVy(0);
        snake.addSquare();
        snake.move();
        snake.addSquare();
        snake.move();
        snake.addSquare();
        snake.move();
        snake.addSquare();
        snake.move();

        snake.setVy(-20);
        snake.setVx(0);
        snake.move();

        snake.setVx(-20);
        snake.setVy(0);
        snake.move();

        snake.setVy(20);
        snake.setVx(0);

        assertTrue(snake.hitSelf());
    }

    @Test
    public void testWin() {
        Snake snake = new Snake(40, 20, 0,0);
        Apple apple = new Apple(40, 20, snake, 20, 0);
        snake.setVx(20);
        if (snake.willIntersect(apple)) {
            snake.addSquare();
            snake.move();
        }

        assertEquals(1, snake.getBodyLength());
        assertTrue(snake.gameWon());
    }



}
