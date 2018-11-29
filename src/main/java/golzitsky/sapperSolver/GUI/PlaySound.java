package golzitsky.sapperSolver.GUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

class PlaySound {

   static boolean playSound = true; //if during the game we choose "Sound turn on" -> true, else -> false.

    /**
     * Play sound after win or losing.
     */
    static void playSound(URL soundFile) {
        if (playSound) {
            try {
                AudioInputStream inAudio = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(inAudio);
                clip.setFramePosition(0);
                clip.start();
            } catch (Exception e) {
                System.out.println("Error! Can't play sound!");
            }
        }
    }
}
