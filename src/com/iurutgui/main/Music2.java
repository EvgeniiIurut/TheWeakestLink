package com.iurutgui.main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music2 implements Runnable {
    private static Music2 instance;
    private Music2() {

    }
    public static void playClip(File clipFile) throws IOException,
            UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        class AudioListener implements LineListener {
            private boolean done = false;
            @Override public synchronized void update(LineEvent event) {
                LineEvent.Type eventType = event.getType();
                if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }
            public synchronized void waitUntilDone() throws InterruptedException {
                while (!done) { wait(); }
            }
        }
        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
        try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try {
                clip.start();
                listener.waitUntilDone();
            } finally {
                clip.close();
            }
        } finally {
            audioInputStream.close();
        }
    }

    @Override
    public void run() {
        try {
            Music2.playClip(new File("C:\\Users\\johny\\Desktop\\JAVA\\TheWeakLink\\3.wav"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (LineUnavailableException e3) {
            e3.printStackTrace();
        } catch (InterruptedException e4) {
            e4.printStackTrace();
        }
    }
    public static Music2 getInstance() {
        if (instance == null) {
            instance = new Music2();
        }
        return instance;
    }
}
