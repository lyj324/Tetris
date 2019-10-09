package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerPoint extends Layer {
	/**
	 * 标题图片
	 */
	private static final Image IMG_POINT =new ImageIcon("Graphies/string/point.png").getImage();
	private static final Image IMG_RMLINE =new ImageIcon("Graphies/string/rmline.png").getImage();
	private static final Image IMG_RECT =new ImageIcon("Graphies/window/rect.png").getImage();
	private static final int POINT_Y=PADDING;
	private static final int RMLINE_Y=IMG_RMLINE.getHeight(null)+(PADDING<<1);
	private static final int POINT_BIT=5;
	private static int POINT_X;
	private  int expY;
	private  int expX;
	private static int LEVERUP=20;
	private static final int RECT_H=IMG_RECT.getHeight(null);
	private static final int RECT_W=IMG_RECT.getWidth(null);
	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		//初始化发分数显示的Y坐标
		POINT_X=this.w-IMG_NUMBER_W*POINT_BIT-PADDING;
		//初始经验值显示Y坐标
		this.expY=RMLINE_Y-16+IMG_RMLINE.getHeight(null)+PADDING;
		this.expX=this.w-(PADDING>>1);
	}
	public void paint(Graphics g) {
		this.createWindow(g);
		//窗口标题(分数)
		g.drawImage(IMG_POINT, this.x+PADDING, this.y+PADDING, null);
		this.drawNumberLeftPad(POINT_X, POINT_Y-16, this.dto.getNowPoint(),POINT_BIT, g);
		//窗口标题(消行)
		g.drawImage(IMG_RMLINE, this.x+PADDING, this.y+RMLINE_Y, null);
		this.drawNumberLeftPad(POINT_X, RMLINE_Y-16, this.dto.getNowRemoveLine(),POINT_BIT, g);
		//经验条
		int rmline=this.dto.getNowRemoveLine();
		//rmline=10;
		drawRect(0,"下一级",null,(double)(rmline%LEVERUP),(double)LEVERUP,g);
	}
	
}
