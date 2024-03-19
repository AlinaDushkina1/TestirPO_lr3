package org.example;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
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
    public Game(){}
    public int getSIZE() {
        return SIZE;
    }

    public int getDOT_SIZE() {
        return DOT_SIZE;
    }

    public int getALL_DOTS() {
        return ALL_DOTS;
    }

    @Override
    public int getX() {
        return x.length;
    }

    @Override
    public int getY() {
        return y.length;
    }

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
}
