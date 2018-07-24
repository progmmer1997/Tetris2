import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameTetris extends JFrame {

    final String TITLE_OF_PROGRAMM = "Tetris";
    final int BLOCK_SIZE = 25;
    final int ARC_RADIUS = 6;
    final int FIELD_WINDTH = 10;
    final int FIELD_HEIGTH = 18;
    final int START_LOCATOIN = 180;
    final int Field_DX = 7;
    final int FIELD_DY = 28;
    final int LEFT = 37;
    final int RIGTH = 39;
    final int UP = 38;
    final int DOWN = 40;
    final int SHOW_DELAY = 400; // delay for animation
    final int[][][] SHAPES = {
            {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {4, 0x00f0f0}}, // I
            {{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {4, 0xf0f000}}, // O
            {{1, 0, 0, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0x0000f0}}, // J
            {{0, 0, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xf0a000}}, // L
            {{0, 1, 1, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0x00f000}}, // S
            {{1, 1, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xa000f0}}, // T
            {{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xf00000}}  // Z
    };
    final int[] SCORES = {100, 300, 700, 1500};
    final int gameScores = 0;
    int[][] mine = new int[FIELD_HEIGTH + 1][FIELD_WINDTH];
    JFrame frame;
    Canvas canvas = new Canvas();
    Random random = new Random();
Figure figure =new Figure();
    boolean gameOver = false;
    final int[][] GAME_OVER_MSG = {
            {0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0},
            {1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0}};

    public static void main(String[] args) {
        new GameTetris().go();
    }

    void go() {
        frame = new JFrame(TITLE_OF_PROGRAMM);
        frame.setVisible(true);
        frame.setLocation(START_LOCATOIN, START_LOCATOIN);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_WINDTH * BLOCK_SIZE + Field_DX, FIELD_HEIGTH * BLOCK_SIZE + FIELD_DY);
        frame.getContentPane().add(BorderLayout.CENTER, canvas);
        canvas.setBackground(Color.MAGENTA);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (!gameOver) {
                    if (e.getKeyCode() == UP) figure.drop();
                    if (e.getKeyCode() == DOWN) figure.rotate();
                    if (e.getKeyCode() == RIGTH || e.getKeyCode() == LEFT) figure.move(e.getKeyCode());
                }
                canvas.repaint();
            }
            @Override
                public void keyReleased (KeyEvent e){}
            }
    });

        while (!gameOver) {
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (InterruptedException e1) {
                e1.printStackTrace();}
        canvas.repaint();
        if (figure.isTochOnTheGround()) {
            figure.leaveOnTheGround();

        }
        }
    }


    }
}



 class Figure {
void drop(){}
void move(int direction){}
 void rotate(){}
    boolean isTochOnTheGround(){
    return false;
    }
    boolean isCrossGround(){
    return false;
    }
void stepDown(){}
void leaveOnTheGround(){}

    }
 class Block {

        }


    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
        }
    }





