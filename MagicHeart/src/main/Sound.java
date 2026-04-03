package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound()
    {
        soundURL[0] = getClass().getResource("/sound/OnTheCoast3.wav");
        soundURL[1] = getClass().getResource("/sound/level1_1.wav");
        soundURL[2] = getClass().getResource("/sound/level1_2.wav");
        soundURL[3] = getClass().getResource("/sound/level2.wav");
        soundURL[4] = getClass().getResource("/sound/level3_1.wav");
        soundURL[5] = getClass().getResource("/sound/level3_2.wav");
    }

    public void setFile(int i)
    {
        try{
            //daca refolosim un sunet, il inchidem
            if (clip != null && clip.isOpen()) {
                clip.stop();
                clip.close();
            }

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

            //volum
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-18.0f);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void setPosition(int seconds)
    {
        if (clip != null)
        {
            long microseconds = seconds * 1_000_000L;
            if (microseconds < clip.getMicrosecondLength())
                clip.setMicrosecondPosition(microseconds);
            else clip.setMicrosecondPosition(0); //daca parametrul intrece durata sunetului, da play de la inceput
        }
    }


    public void play()
    {
        clip.start();
    }
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop()
    {
        clip.stop();
    }
}
