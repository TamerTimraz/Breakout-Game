package game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    private Paddle paddle;
    private Bricks bricks;
    private Ball ball;
    private Graphics graphics;
    private JFrame window;

    public static final int width = 360;
    public static final int height = 450;

    public Game(){
        window = new JFrame();

        paddle = new Paddle();
        bricks = new Bricks();
        ball = new Ball(this);
        graphics = new Graphics(this);

        window.add(graphics);

        window.setTitle("Breakout");
        window.setSize(width, height);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if(graphics.state == "START") {
            if (keycode == KeyEvent.VK_SPACE) {
                this.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void start(){
        graphics.state = "RUNNING";
    }

    public void update(){
        if(graphics.state == "RUNNING"){
            ball.moveBall();
        }
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public Bricks getBricks() {
        return bricks;
    }

    public void setBricks(Bricks bricks) {
        this.bricks = bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public JFrame getWindow() {
        return window;
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

}
