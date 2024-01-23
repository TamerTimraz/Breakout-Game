package game;


import java.awt.*;

public class Paddle {

    private int paddleX = 150;
    private int paddleY = 350;

    public Paddle(){
        Rectangle p = new Rectangle(paddleX, paddleY, 80, 20);
    }

    public int getPaddleX() {
        return paddleX;
    }

    public void setPaddleX(int paddleX) {
        this.paddleX = paddleX;
    }

    public int getPaddleY() {
        return paddleY;
    }

    public void setPaddleY(int paddleY) {
        this.paddleY = paddleY;
    }
}
