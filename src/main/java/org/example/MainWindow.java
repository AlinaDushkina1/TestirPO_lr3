package org.example;

import javax.swing.*;

public class MainWindow extends JFrame {
    private String title="Змейка";
    private int width=320;
    private int height=345;
    private int x=400;
    private int y=400;
    private boolean visible=true;
    public MainWindow(){
        setTitle(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width,height);
        setLocation(x,y);
        setVisible(visible);
    }

    public boolean getVisible() { return visible;}

    public static void main(String[] args) {
        MainWindow window=new MainWindow();
    }
}