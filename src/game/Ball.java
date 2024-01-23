package game;

import java.awt.*;
import java.util.ArrayList;

public class Ball{

    private Paddle paddle;
    private Game game;
    private Bricks bricks;
    private Graphics graphics;

    private int ballX = 150;
    private int ballY = 250;
    private int ballSpeedX = 8;
    private int ballSpeedY = 8;

    public Ball(Game g){
        Rectangle b = new Rectangle(ballX,ballY,10,10);
        game = g;
        paddle = g.getPaddle();
        bricks = g.getBricks();
        graphics = g.getGraphics();
    }

    public void moveBall(){
        if(!bricks.getBricks().isEmpty()) {
            // update ball's position based on speed
            ballX += ballSpeedX;
            ballY += ballSpeedY;

            if (checkWallCollision() == 1) {
                ballSpeedX = -ballSpeedX;
            } else if (checkWallCollision() == 2) {
                ballSpeedY = -ballSpeedY;
            } else if (checkWallCollision() == 3) {
                Graphics.state = "END";
            }

            if (checkPaddleCollision()) {
                // calculate relative position of ball on paddle
                double relativeIntersectX = (paddle.getPaddleX() + 40) - ballX;
                double normalizedRelativeIntersectionX = relativeIntersectX / 60.0;
                double bounceAngle = normalizedRelativeIntersectionX * Math.PI / 3;

                // change the balls direction based on the calculated angle
                ballSpeedX = (int) (Math.sin(bounceAngle) * -8);
                ballSpeedY = (int) (Math.cos(bounceAngle) * -8);
            }

            checkBrickCollision();

        } else {
            Graphics.state = "WIN";
        }



    }

    // checks if the ball collides with a wall
    public int checkWallCollision(){
        if(ballX <= 0 || ballX + 10 >= Game.width){ // side collision
            return 1;
        } else if(ballY <= 0){ // top collision
            return 2;
        } else if(ballY >= Game.height + 10){ // misses paddle and goes out of bounds
            return 3;
        } else{  // no collision
            return 0;
        }
    }

    // checks if the ball collides with the paddle
    public boolean checkPaddleCollision(){


        if(ballY + 5 >= paddle.getPaddleY() - 10 && ballY + 5 <= paddle.getPaddleY() + 10
                && ballX + 5 >= paddle.getPaddleX() - 40 && ballX <= paddle.getPaddleX() + 80){
            return true;
        } else {
            return false;
        }
    }

    // checks if the ball collides with any brick
    public void checkBrickCollision(){
        ArrayList<Integer> bricksToRemove = new ArrayList<>();

        for(int i = 0; i < bricks.getBricks().size(); i++){
            int brickX = bricks.getX(i);
            int brickY = bricks.getY(i);

            int threshold = 5;

            // check for collision on top side of brick
            if(ballX + 20 >= brickX - threshold && ballX <= brickX + 50 + threshold &&
                    ballY + 20 >= brickY - threshold && ballY <= brickY + threshold){
                bricksToRemove.add(i);
                ballSpeedY = -ballSpeedY;
            }
            // check for collision on bottom side of brick
            else if(ballX + 20 >= brickX - threshold && ballX <= brickX + 50 + threshold &&
                    ballY >= brickY + 20 - threshold && ballY <= brickY + 20 + threshold){
                bricksToRemove.add(i);
                ballSpeedY = -ballSpeedY;
            }
            // check for collision on the left side of the brick
            else if (ballX >= brickX + 50 - threshold && ballX <= brickX + 50 + threshold &&
                    ballY + 20 >= brickY - threshold && ballY <= brickY + 20 + threshold) {
                bricksToRemove.add(i);
                ballSpeedX = -ballSpeedX;
            }

            // check for collision on the right side of the brick
            else if (ballX + 20 >= brickX - threshold && ballX <= brickX + threshold &&
                    ballY + 20 >= brickY - threshold && ballY <= brickY + 20 + threshold) {
                bricksToRemove.add(i);
                ballSpeedX = -ballSpeedX;
            }
        }

        // remove marked bricks after the iteration is complete
        for(int index : bricksToRemove){
            bricks.getBricks().remove(index);
        }
    }

    public int getX() {
        return ballX;
    }

    public void setX(int x) {
        this.ballX = x;
    }

    public int getY() {
        return ballY;
    }

    public void setY(int y) {
        this.ballY = y;
    }
}
