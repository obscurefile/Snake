package com.snake.core;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
	
	public void playAudio(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
		
		Clip clip = AudioSystem.getClip();
		
		clip.open(audio);
		clip.start();
	}
}