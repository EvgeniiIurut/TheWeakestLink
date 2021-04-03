package com.iurutgui.main;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Panel extends JPanel {

    private int randomNum;
    private JButton numbers[] = new JButton[8];
    private Font font = new Font("SanSerif", Font.BOLD, 20);
    private JButton outputBank = new JButton();
    private Timer timer;
    private JButton bank = new JButton("BANK");
    private JButton pass = new JButton("PASS");
    private JButton next = new JButton("NEXT");
    private JButton start = new JButton("START");
    private JButton stop = new JButton("STOP");
    private JButton musicButton1 = new JButton("MUSIC START");
    private JButton musicButton2 = new JButton("MUSIC END");
    private JButton restartButton = new JButton("RESTART GAME");
    private JButton changeQuestions = new JButton("CHANGE QUESTIONS");
    private JTextArea jTextArea = new JTextArea();
    private Integer k_question = 1;
    private Integer number_question = 0;
    private String[] stringsURL = new String[10];
    private Integer stringsUrlNumber = 0;
    private ArrayList<String> arrayList = Parser.parseSite("http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2020-12-25)");
    private Music music;
    private Music1 music1;
    private Music2 music2;
    private Thread musicThread;
    private Thread musicThread1;
    private Thread musicThread2;

    private Image backgroundImage;


    private Integer seconds = 120;
    private JButton currentJButton;


//    private List<Character> charsAll = Arrays.asList('+', '-', '*', '/','(',')','.');


    public Panel(String str) {
        stringsURL[0] = "http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2020-12-25)";
        stringsURL[1] = "http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2021-01-22)";
        stringsURL[2] = "http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2021-01-29)";
        stringsURL[3] = "http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2021-02-05)";
        stringsURL[4] = "http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2021-02-12)";
        stringsURL[5] = "http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2021-02-19)";
        stringsURL[6] = "http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2021-02-26)";
        stringsURL[7] = "http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2021-03-05)";
        stringsURL[8] = "http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2021-03-12)";
        stringsURL[9] = "http://gameshows.ru/wiki/%D0%A1%D0%BB%D0%B0%D0%B1%D0%BE%D0%B5_%D0%B7%D0%B2%D0%B5%D0%BD%D0%BE_(%D0%9E%D0%B1%D0%B7%D0%BE%D1%80_%D0%B2%D1%8B%D0%BF%D1%83%D1%81%D0%BA%D0%B0_2021-03-19)";

        setLayout(null); // allow to set any location of buttons
        setFocusable(true);
        grabFocus();

        try {
            backgroundImage = ImageIO.read(new File(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel jLabel = new JLabel("120");
        jLabel.setFont(font);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Border border = BorderFactory.createLineBorder(Color.RED, 5);
        jLabel.setBorder(border);
        jLabel.setOpaque(true);
        jLabel.setBounds(600, 500, 100, 50);
        add(jLabel);
        start.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (musicThread != null && musicThread.isAlive()) {
                    musicThread.stop();
                }
                if (timer == null) {
                    timer = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            seconds--;
                            if (seconds <= 0) timer.stop();
                            jLabel.setText(String.format("%03d", seconds));
                        }
                    });
                }
                if (!timer.isRunning() && music == null) {
                    timer.start();
                    music = Music.getInstance();
                    if (musicThread != null && musicThread.isAlive()) {
                        musicThread.stop();
                    }
                    musicThread = new Thread(music);
                    musicThread.start();
                }
                if (seconds <= 0) {
                    seconds = 120;
                    musicThread.stop();
                }

            }
        }));

        musicButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                music1 = Music1.getInstance();
                if (musicThread1 != null && musicThread1.isAlive()) {
                    musicThread1.stop();
                }
                musicThread1 = new Thread(music1);
                musicThread1.start();
            }
        });
        musicButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                music2 = Music2.getInstance();
                if (musicThread2 != null && musicThread2.isAlive()) {
                    musicThread2.stop();
                }
                musicThread2 = new Thread(music2);
                musicThread2.start();
                if (musicThread1 != null && musicThread1.isAlive()) {
                    musicThread1.stop();
                }
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                    timer = null;
                    music = null;
                }
                if (musicThread != null && musicThread.isAlive()) {
                    musicThread.stop();
                    music = null;
                }
                if (musicThread1 != null && musicThread1.isAlive()) {
                    musicThread1.stop();
                    music1 = null;
                }
                if (musicThread2 != null && musicThread2.isAlive()) {
                    musicThread2.stop();
                    music2 = null;
                }
                seconds = 120;
                jLabel.setText(String.format("%03d", seconds));
                if (musicThread1 != null && musicThread1.isAlive()) {
                    musicThread1.stop();
                }
                if (musicThread2 != null && musicThread2.isAlive()) {
                    musicThread2.stop();
                }
                if (musicThread != null && musicThread.isAlive()) {
                    musicThread.stop();
                }
            }
        });


        //---------------
        bank.setBounds(1050, 100, 100, 50);
        bank.setFont(font);
        add(bank);
        //---------------
        pass.setBounds(1050, 160, 100, 50);
        pass.setFont(font);
        add(pass);
        //---------------
        next.setBounds(1050, 220, 100, 50);
        next.setFont(font);
        add(next);
        //---------------
        start.setBounds(1050, 300, 100, 50);
        start.setFont(font);
        add(start);
        //---------------
        musicButton1.setBounds(1050, 500, 200, 50);
        musicButton1.setFont(font);
        add(musicButton1);
        //---------------
        musicButton2.setBounds(1050, 575, 200, 50);
        musicButton2.setFont(font);
        add(musicButton2);
        //---------------
        changeQuestions.setBounds(700, 50, 250, 50);
        changeQuestions.setFont(font);
        add(changeQuestions);
        //---------------
        restartButton.setBounds(350, 50, 200, 50);
        restartButton.setFont(font);
        add(restartButton);
        //---------------
        stop.setBounds(1050, 360, 100, 50);
        stop.setFont(font);
        add(stop);
        //---------------
        outputBank.setBounds(85, 580, 125, 50);
        outputBank.setFont(font);
        add(outputBank);
        outputBank.setText("0");
        //---------------

        jTextArea.setBounds(250, 200, 750, 100);
        jTextArea.setEditable(false);
        jTextArea.setFont(font);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        add(jTextArea);
        jTextArea.setText(arrayList.get(0));

        //---------------
        for (int i = 0; i < 8; i++) {
            numbers[i] = new JButton(i * 2 + 1 + "000");
            numbers[i].setBounds(100, 400 - i * (60) + 70, 100, 50);
            numbers[i].setFont(font);
            add(numbers[i]);
        }

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                    timer = null;
                    music = null;
                }
                if (musicThread != null && musicThread.isAlive()) {
                    musicThread.stop();
                }
                if (musicThread1 != null && musicThread1.isAlive()) {
                    musicThread1.stop();
                    music1 = null;
                }
                if (musicThread2 != null && musicThread2.isAlive()) {
                    musicThread2.stop();
                    music2 = null;
                }
                seconds = 120;
                jLabel.setText(String.format("%03d", seconds));
                outputBank.setText("0");
                if (musicThread1 != null && musicThread1.isAlive()) {
                    musicThread1.stop();
                }
                if (musicThread2 != null && musicThread2.isAlive()) {
                    musicThread2.stop();
                }
                if (musicThread != null && musicThread.isAlive()) {
                    musicThread.stop();
                }
                k_question = 0;
                jTextArea.setText(arrayList.get(k_question));
                k_question++;
            }
        });
        changeQuestions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stringsUrlNumber++;
                Parser.arrayList = new ArrayList<>();
                arrayList = Parser.parseSite(stringsURL[stringsUrlNumber]);
                k_question = 0;
                jTextArea.setText(arrayList.get(k_question));
                k_question++;
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    jTextArea.setText(arrayList.get(k_question));
                    k_question++;
                    number_question++;
                    for (int i = 0; i < number_question; i++) {
                        numbers[i].setBounds(100, 450 - i * (40) + 70, 100, 50);
                        currentJButton = numbers[i];
                    }
                } catch (Exception b) {
                    System.out.println("Значение отсутстует");
                    jTextArea.setText("Ошибка ввода выражения");
                }
            }
        });

        bank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String temp = outputBank.getText();
                    outputBank.setText(Integer.parseInt(temp) + Integer.parseInt(currentJButton.getText()) + "");
                    number_question = 0;
                    for (int i = 0; i < 8; i++) {
                        numbers[i].setBounds(100, 400 - i * (60) + 70, 100, 50);
                    }
                } catch (Exception b) {
                    System.out.println("Значение отсутстует");
                    jTextArea.setText("Ошибка ввода выражения");
                }
            }
        });

        pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    randomNum = ThreadLocalRandom.current().nextInt(0, arrayList.size());
                    jTextArea.setText(arrayList.get(randomNum));
                    arrayList.remove(randomNum);
                    k_question++;
                    number_question = 0;
                    for (int i = 0; i < 8; i++) {
                        numbers[i].setBounds(100, 400 - i * (60) + 70, 100, 50);
                    }
                } catch (Exception b) {
                    System.out.println("Значение отсутстует");
                    jTextArea.setText("Ошибка ввода выражения");
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

}
