package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private Image snake;
    private Image headSnake;
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
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    /**
     * Метод - загрузка изображений
     */
    public void loadImages() {
        ImageIcon icon_apple=new ImageIcon("apple1.png");
        apple = icon_apple.getImage();
        ImageIcon icon_snake=new ImageIcon("snake.png");
        headSnake = icon_snake.getImage();
        ImageIcon icon_snake1=new ImageIcon("snake1.png");
        snake = icon_snake1.getImage();
    }

    /**
     * Метод - инициализация игры
     * Установление начальных позиций змейки
     * Создание Яблока
     */
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

    /**
     * Метод - создания яблока на рандомной позиции
     */
    public void createApple(){
        appleX=new Random().nextInt(20)*DOT_SIZE;
        appleY=new Random().nextInt(20)*DOT_SIZE;
    }

    /**
     * Метод, отвечающий за логическую перерисовку точек
     * Т.е. они будут сдвигаться массиве, заданном для хранения позиций ячеек
     */
    public void moveSnake(){
        for(int i = dots; i>0; i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if(left){x[0]-=DOT_SIZE;}
        if(right){x[0]+=DOT_SIZE;}
        if(up){y[0]-=DOT_SIZE;}
        if(down){y[0]+=DOT_SIZE;}
    }

    /**
     * Имплементированный метод, который вызывается каждый раз, когда тикает таймер
     * Каждые 250 милисекунд
     * Если в игре - проверяется на столкновение с рамками поля
     * Также проверка на встречу с яблоком, если это так,
     * то змея увелич и генерируется новое яблоко
     * движение змеи
     * А также перерисовка поля
     * @param e событие, подлежащее обработке
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            moveSnake();
        }
        repaint();
    }

    /**
     * Метод - проверка столкновения с яблоком
     */
    public void checkApple(){
        if(x[0] == appleX && y[0]==appleY){
            dots++;
            createApple();
        }
    }
    /**
     * Метод - проверка столкновения с собой или
     * выход за пределы иггрового поля
     */
    public void checkCollisions(){
        for(int i=dots;i>0;i--){
            if(i>4 && x[0]==x[i] && y[0]==y[i]){
                inGame=false;
            }
        }
        if(x[0]>SIZE){ inGame = false;}
        if(x[0]<0){ inGame = false;}
        if(y[0]>SIZE){ inGame = false;}
        if(y[0]<0){ inGame = false;}
    }
    /**
     * Переопределённый метод, который отрисовывает игровое поле
     * Перерисовывается только то что касается игры
     * Если ещё в игре: Сначала рисуем яблоко, Затем периросывам змейку
     * Иначе: игра заканчивается и появляется надпись - Game Over
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(inGame){
            g.drawImage(apple,appleX,appleY,this);
            g.drawImage(headSnake,x[0],y[0],this);
            for(int i = 1; i<dots;i++){
                g.drawImage(snake,x[i],y[i],this);
            }
        }
        else {
            String str = "Game Over";
            g.setColor(Color.white);
            g.drawString(str,125,SIZE/2);
        }
    }
    public void setX(int i, int element){x[i]=element;}
    public void setY(int i, int element){y[i]=element;}
    public void setDots(int dots) {this.dots = dots;}
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
    public int getYlength() {return y.length;}

    public int getX(int i) {return x[i];}
    public int getY(int i) {return y[i];}
    public boolean isLeft() {return left;}

    public boolean isRight() {return right;}

    public boolean isUp() {return up;}

    public boolean isDown() {return down;}

    public boolean isInGame() {return inGame;}
    public Image getSnake() {return snake;}
    public Image getHeadSnake() {return headSnake;}

    public Image getApple() {return apple;}

    public int getAppleX() {return appleX;}

    public int getAppleY() {return appleY;}

    public int getDots() {return dots;}

    public Timer getTimer() {return timer;}

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key==KeyEvent.VK_LEFT && !right){
                left= true;
                up=false;
                down = false;
            }
            if(key==KeyEvent.VK_RIGHT && !left){
                right= true;
                up=false;
                down = false;
            }
            if(key==KeyEvent.VK_UP && !down){
                right= false;
                up=true;
                left = false;
            }
            if(key==KeyEvent.VK_DOWN && !up){
                left= false;
                down=true;
                right = false;
            }
        }
    }
}
