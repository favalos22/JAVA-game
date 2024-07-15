package Sound;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;

public class Sound {
	//Fields
	private String _str;
	private Clip _clip;
	private AudioFormat format;
	private DataLine.Info info;
	private AudioInputStream stream;
	
	//Constructor
	public Sound(String fileName) {
		_str = fileName;
		File yourFile = new File(_str);
		try {
			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			_clip = (Clip) AudioSystem.getLine(info);
			_clip.open(stream);
			_clip.stop();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Audio not detected or Audio problem.. Exiting");
			System.exit(0);
		}
	}
	
	//Methods
	public String getSongFileName() {
		return _str;
	}
	
	public boolean isFinished() {
		if(_clip.getMicrosecondPosition() >= _clip.getMicrosecondLength())
			return true;
		return false;
	}
	
	public boolean isPlaying() {
		return _clip.isRunning();
	}
	
	public void resumeWAV() {
		_clip.start();
	}
	
	public long getAudioLength() {
		return _clip.getMicrosecondLength();
	}
	
	public long getAudioPosition() {
		return _clip.getMicrosecondPosition();
	}
	
	public void playWAV() {
		if(_clip.isActive()) {
			_clip.stop();
			_clip.setMicrosecondPosition(0);
		}
		if(_clip.getMicrosecondPosition() >= _clip.getMicrosecondLength())
			_clip.setMicrosecondPosition(0);
		_clip.start();
	}
	
	public void resetWAV() {
		_clip.setMicrosecondPosition(0);
	}
	
	public void flush() {
		_clip.stop();
		_clip.flush();
		_clip.close();
	}
	
	public void restartWAV() {
		_clip.setMicrosecondPosition(0);
		_clip.start();
	}
	
	public void pauseWAV() {
		if(_clip.isRunning()) {
			_clip.stop();
		}
	}
	
	public void setLoop() {
		_clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
