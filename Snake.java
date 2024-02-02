package org.cis1200.snake;

import java.awt.*;
import java.util.LinkedList;

public class Snake extends BoardObj {

    private LinkedList<Square> squares;

    //snake is represented by the head
    public static final int SIZE = 20;
    private int vx, vy;
    public int px, py;

    private int boardWidth;
    private int boardHeight;

    public Snake(int boardWidth, int boardHeight, int px, int py) {
        super(0, 0, SIZE, SIZE, boardWidth, boardHeight);
        this.squares = new LinkedList<>();
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.px = px;
        this.py = py;


    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(px, py, SIZE, SIZE);
        g.setColor(Color.BLACK);
        for (Square s: squares) {
            s.draw(g);
        }
    }
    public  int getVx() {
        return this.vx;
    }

    public  int getVy() {
        return this.vy;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public void move() {
        if (vx != 0 || vy != 0) {
            for (int i = squares.size() - 1; i >= 0; i--) {
                Square s = squares.get(i);
                if (i == 0) {
                    s.setPx(this.px);
                    s.setPy(this.py);

                } else {
                    Square prev = squares.get(i - 1);
                    s.setPx(prev.getPx());
                    s.setPy(prev.getPy());
                }
            }
        }
        this.px += this.vx;
        this.py += this.vy;
        clip();
    }

    public void addSquare() {
        Square n = new Square(boardWidth, boardHeight);
        squares.add(n);
    }


    public boolean willIntersect(BoardObj that) {
        int thisNextX = this.px + this.vx;
        int thisNextY = this.py + this.vy;
        int thatNextX = that.getPx();
        int thatNextY = that.getPy();

        return (thisNextX == thatNextX && thisNextY == thatNextY);
    }

    public boolean hitSelf() {
        for (int i = 1; i < squares.size(); i++) {
            Square body = squares.get(i);
            if (willIntersect(body)) {
                return true;
            }
        }
        return false;
    }
    public boolean willHitWall() {
        if (this.px + this.vx < 0) {
            return true;
        } else if (this.px + this.vx > this.getMaxX()) {
            return true;
        }

        if (this.py + this.vy < 0) {
            return true;
        } else {
            return (this.py + this.vy > this.getMaxY());
        }
    }


    /**
     * Moves the object by its velocity. Ensures that the object does not go
     * outside its bounds by clipping.
     */
    private void clip() {
        this.px = Math.min(Math.max(this.px, 0), this.getMaxX());
        this.py = Math.min(Math.max(this.py, 0), this.getMaxY());
    }

    public boolean gameWon() {
        return (getBodyLength() == (boardWidth / SIZE) * (boardHeight / SIZE) - 1);
    }

    public int getBodyLength() {
        return squares.size();
    }

    public Square getSquare(int i) {
        return squares.get(i);
    }
}