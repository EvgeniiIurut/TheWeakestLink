package com.iurutgui.main;

import javax.swing.*;

public class Main {
    private JFrame window;

    public Main(){
        window = new JFrame("The Weakest Link");
        window.setSize(1300,700); // set size of frame
        window.setLocationRelativeTo(null); // set location of fame to center
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.getContentPane().add(new Panel("C:\\Users\\johny\\Desktop\\JAVA\\TheWeakLink\\The.png"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}


/*TODO
    +1 Pharsing of questions from http://gameshows.ru/
    +2 Restart
    3 Some dusty phrases about players
    4 GitHub
*/