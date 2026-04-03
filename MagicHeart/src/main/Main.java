package main;

import javax.swing.*;
import java.io.IOException;

public class Main
{

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Magic Heart");
        ImageIcon logo = new ImageIcon(Main.class.getClassLoader().getResource("gems/icon.png"));
        window.setIconImage(logo.getImage());

        GamePanel gamePanel = new GamePanel(window);
        window.add(gamePanel);
        window.pack();


        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }


}
