package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class LayerBackgroung extends Layer {
	private static Image IMG_BG_TEMP =new ImageIcon("Graphies/background/bg01.jpg").getImage();
	public static List<Image> BG_LIST;
	static {
		//±³¾°Í¼Æ¬
		File dir =new File(System.getProperty("user.dir")+"/Graphies/background");
		File[] files =dir.listFiles();
		BG_LIST=new ArrayList<Image>();
		for(File file:files) {
			if(!file.isDirectory())
			BG_LIST.add(new ImageIcon(file.getPath()).getImage());
		}
	}
	public LayerBackgroung(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		int BG_IDX=this.dto.getLevel()%BG_LIST.size();
		g.drawImage(BG_LIST.get(BG_IDX), 0, 0,1162,654, null);
	}

}
