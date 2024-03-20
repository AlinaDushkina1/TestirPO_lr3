package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game extends JPanel implements ActionListener {
    /**
     * размер поля
     */
    private final int SIZE = 320;
    /**
     * размер в пикселях, сколько будет занимать одна ячейка змейки и еды змейки
     */
    private final int DOT_SIZE = 16;
    /**
     * максимальное кол-во помещающихся 16-ти пиксельных ячеек в поле размером 320
     */
    private final int ALL_DOTS = 400;

    /**
     * Image под игровую ячейку
     */
    private Image dot;
    /**
     * Image под еду-яблоко
     */
    private Image apple;
    /**
     * x позиция яблока
     */
    private int appleX;
    /**
     * y позиция яблока
     */
    private int appleY;
    /**
     * массивы для хранения положений змейки
     */
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    /**
     * размер змейки
     */
    private int dots;
    /**
     * таймер
     */
    private Timer timer;
    /**
     * поля, отвечающие за текущее направление движения змейки:
     */
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    /**
     * поле, отвечающее за статус - в игре мы или же уже нет
     */
    private boolean inGame = true;
    public Game(){
        setBackground(Color.black);
        loadImages();
    }
    public void loadImages() {
        ImageIcon icon_apple=new ImageIcon("apple.png");
        apple = icon_apple.getImage();
        ImageIcon icon_dot=new ImageIcon("dot.png");
        dot = icon_dot.getImage();
    }
    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i*DOT_SIZE;//Т.е. начальная х позиция на числе 48, т к она кратна 16
            y[i] = 48;
        }
        timer = new Timer(250,this);//то с какой частотой будет тикать
        timer.start();
        createApple();
    }
    public void createApple(){
        appleX=new Random().nextInt(20)*DOT_SIZE;
        appleY=new Random().nextInt(20)*DOT_SIZE;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){

        }
        repaint();
    }

    public int[] getXmassiv() {return x;}
    public int[] getYmassiv() {return y;}

    public int getSIZE() {
        return SIZE;
    }

    public int getDOT_SIZE() {
        return DOT_SIZE;
    }

    public int getALL_DOTS() {
        return ALL_DOTS;
    }

    public int getXlength() {
        return x.length;
    }
    public int getYlength() {
        return y.length;
    }

    public int getX(int i) {return x[i];}
    public int getY(int i) {return y[i];}
    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isInGame() {
        return inGame;
    }

    public Image getDot() {return dot;}

    public Image getApple() {return apple;}

    public int getAppleX() {return appleX;}

    public int getAppleY() {return appleY;}

    public int getDots() {return dots;}

    public Timer getTimer() {return timer;}
}
