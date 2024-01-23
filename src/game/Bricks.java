package game;

import java.awt.*;
import java.util.ArrayList;

public class Bricks {
    public static ArrayList<Rectangle> bricks;
    public static final int brickWidth = 60;
    public static final int brickHeight = 20;

    public Bricks(){
        bricks = new ArrayList<>();

        for(int i = 0; i < 30; i++){
            bricks.add(new Rectangle(brickWidth, brickHeight));
        }

        int x1 = 0;
        int x2 = 0;
        int x3 = 0;
        int x4 = 0;
        int x5 = 0;
        for(int i = 0; i < bricks.size(); i++){
            if(i < 6) {
                bricks.get(i).setLocation(x1, 0);
                x1 += 60;
            } else if(i < 12){
                bricks.get(i).setLocation(x2, 20);
                x2 += 60;
            } else if(i < 18){
                bricks.get(i).setLocation(x3, 40);
                x3 += 60;
            } else if(i < 24){
                bricks.get(i).setLocation(x4, 60);
                x4 += 60;
            } else{
                bricks.get(i).setLocation(x5, 80);
                x5 += 60;
            }
        }
    }

    public ArrayList<Rectangle> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Rectangle> bricks) {
        this.bricks = bricks;
    }

    public int getX(int i){
        return bricks.get(i).x;
    }

    public int getY(int i){
        return bricks.get(i).y;
    }
}
