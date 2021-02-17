package com.romero.game;

import javax.swing.JFrame;

public class Window extends JFrame{

    public Window(){
        setTitle("Game Launcher: Vlad's Java Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//        Allows you to exit the frame
        setContentPane(new GamePanel(1280, 720));
        pack();//        Condenses the window (Ex. JPanel)
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
