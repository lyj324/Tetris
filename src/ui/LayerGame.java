package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import org.dom4j.Branch;

import entity.GameAct;

public class LayerGame extends Layer {
	private static Image ACT =new ImageIcon("Graphies/game/rect.png").getImage();
	private static Image shadow=new ImageIcon("Graphies/game/shadow.png").getImage();
	private static Image pause=new ImageIcon("Graphies/string/pause.png").getImage();
	/**
	 * ��λ��
	 */
	private static final int SIZE_ORL=5;
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	public void paint(Graphics g) {
		this.createWindow(g);
		GameAct act= this.dto.getGameAct();
		if(act!=null) {
			Point[] points=act.getActPoints();
			//������Ӱ
			this.drawShadow(points,this.dto.isShadow(),g);
			//���ƻ����
			this.drawMainAct(points,g);	
		}
			//���Ƶ�ͼ
			this.drawMap(g);
			//��ͣ
			if(this.dto.isPause()) {
				g.drawImage(pause, this.x+(this.w-pause.getWidth(null)>>1) ,this.y+(this.h-pause.getHeight(null)>>1), null);
			}
	}
	/**
	 * ���Ƶ�ͼ
	 * @param g
	 */
	private void drawMap(Graphics g) {
		//��ӡ��ͼ
				boolean[][] map=this.dto.getGameMap();
				int imgIDX=this.dto.getLevel();
				imgIDX=(imgIDX==0?0:(this.dto.getLevel()+6)%7+1);
				for(int x=0;x<map.length;x++) {
					for(int y=0;y<map[x].length;y++) {
						if(map[x][y])
							drawActByPoint(x, y,this.dto.isStart()?imgIDX:8, g);
					}
				}
	}
	/**
	 * ���ƻ����
	 * @param g
	 */
	private void drawMainAct(Point[] points,Graphics g) {
		//��÷������ͱ��(0~6)
		int typeCode=this.dto.getGameAct().getTypeCode();
		typeCode=this.dto.isStart()?typeCode:7;
		//��ӡ����
		for(int i=0;i<points.length;i++) {
			drawActByPoint(points[i].x, points[i].y, typeCode+1, g);
		}
	}
	/**
	 * ������Ӱ
	 * @param points
	 * @param b
	 */
	private void drawShadow(Point[] points, boolean b,Graphics g) {
		if(!b)return;
		int leftX=9;
		int rightX=0;
		for(Point p:points) {
			leftX=p.x<leftX?p.x:leftX;
			rightX=p.x>rightX?p.x:rightX;
		}
		g.drawImage(shadow, this.x+SIZE+leftX*32, this.y+SIZE, (rightX-leftX+1)*32, this.h-(SIZE<<1), null);
		//g.drawRect(this.x+SIZE+leftX*32, this.y+SIZE, (rightX-leftX+1)*32, this.h-(SIZE<<1));
	}
	/**
	 * ���Ʒ��ο�
	 * @param x
	 * @param y
	 * @param imgIdex
	 * @param g
	 */
	private void drawActByPoint(int x,int y,int imgIdex,Graphics g) {
		g.drawImage(ACT,
				this.x+(x<<SIZE_ORL)+7,
				this.y+(y<<SIZE_ORL)+7,
				this.x+(x+1<<SIZE_ORL)+7,
				this.y+(y+1<<SIZE_ORL)+7,
				imgIdex<<SIZE_ORL,
				0,
				(1+imgIdex)<<SIZE_ORL,
				1<<SIZE_ORL,
				null);
	}
}
