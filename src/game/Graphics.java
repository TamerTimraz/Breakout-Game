package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.*;

public class Graphics extends JPanel implements ActionListener {

    private Timer timer = new Timer(50, this);

    private Paddle paddle;
    private Bricks bricks;
    private Ball ball;
    private Game game;

    public static String state;

    public Graphics(Game g){
        timer.start();
        state = "START";

        game = g;
        paddle = g.getPaddle();
        bricks = g.getBricks();
        ball = g.getBall();

        // add a key listener
        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        // add a mouse listener
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                paddle.setPaddleX(e.getX());
            }
        });

    }

    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0, Game.width, Game.height);

        if(state == "START"){
            g2d.setColor(Color.WHITE);
            g2d.drawString("Welcome to Breakout!", Game.width/2 - 63 , Game.height/2 - 50);
            g2d.drawString("Break all the bricks to win.", Game.width/2 - 75 , Game.height/2 - 30);
            g2d.drawString("Press Space To Start", Game.width/2 - 60 , Game.height/2 - 10);
        } else if(state == "RUNNING"){
            g2d.setColor(Color.RED);
            g2d.fillOval(ball.getX(), ball.getY(), 10, 10);

            g2d.setColor(Color.CYAN);
            for(int i = 0; i < bricks.getBricks().size(); i++){
                g2d.drawRect(bricks.getX(i), bricks.getY(i), Bricks.brickWidth, Bricks.brickHeight);
                g2d.fillRect(bricks.getX(i) + 1, bricks.getY(i) + 1, Bricks.brickWidth - 1 , Bricks.brickHeight - 1);
            }

            g2d.setColor(Color.BLUE);
            g2d.fillRect(paddle.getPaddleX(), paddle.getPaddleY(), 80, 20);

        } else if(state == "END"){
            g2d.setColor(Color.WHITE);
            g2d.drawString("Game Over! Your Score is " + (30 - bricks.getBricks().size()), Game.width/2 - 85, Game.height/2);
        } else {
            g2d.setColor(Color.WHITE);
            g2d.drawString("You Win!! Your Score is " + (30 - bricks.getBricks().size()), Game.width/2 - 85, Game.height/2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        game.update();
    }
}
