package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import dto.Player;

public class LayerDataBase extends Layer{
	private static final int MAX_ROW =5;
	private static int STAR_Y =5;
	/**
	 * ¼ä¾à
	 */
	private static int SPA =0;
	private static Image IMG_DB =new ImageIcon("Graphies/string/db.png").getImage();
	public LayerDataBase(int x, int y, int w, int h) {
		super(x, y, w, h);
		SPA=(this.h-(RECT_H+4)*5-(PADDING<<1)-IMG_DB.getHeight(null))/MAX_ROW;
		STAR_Y=this.y+PADDING+IMG_DB.getHeight(null)+SPA;
	}
	
	public void paint(Graphics g) {
		this.createWindow(g);
		List<Player> players=this.dto.getDbRecode(); 
		int nowPoint=this.dto.getNowPoint();
		g.drawImage(IMG_DB, this.x+PADDING, this.y+PADDING, null);
		for (int i = 0; i < MAX_ROW; i++) {
			Player p = null;
			if(players.size()>i) {
			p= players.get(i);
			this.drawRect(-165+STAR_Y+i*(RECT_H+4+SPA),p.getName()!=null?p.getName():"NO DATA",Integer.toString(p.getPoint()), nowPoint, p.getPoint(), g);
			}
		}
	}
}
