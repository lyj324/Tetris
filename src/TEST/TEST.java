package TEST;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class TEST {
	public static void main(String[] args) {
		File file =new File("Bgm/01.mp3");
        Player player;
		try {
			player = new Player(new FileInputStream(file)); 
			while(!player.isComplete()) {player.play();System.out.println(!player.isComplete());}
		} catch (FileNotFoundException | JavaLayerException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
