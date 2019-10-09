package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.security.ProtectionDomain;
import java.util.Random;

import javax.swing.ImageIcon;

import config.ConfigFactory;
import config.GameConfig;
import dto.GameDto;

/**
 * 
 * 绘制窗口
 * @author user
 *
 */
public abstract class Layer {
	protected static final int PADDING;
	protected static final int SIZE;
	static{
		GameConfig cfg=ConfigFactory.getGameConfig();
		PADDING=cfg.getPadding();
		SIZE=cfg.getWindowSize();
	}
	private static Image WINDOW_IMG =new ImageIcon("Graphies/window/Window.png").getImage();
	private static int WINDOW_W=WINDOW_IMG.getWidth(null);
	private static int WINDOW_H=WINDOW_IMG.getHeight(null);
	private static final Image IMG_RMLINE =new ImageIcon("Graphies/string/rmline.png").getImage();
	private static final Image IMG_RECT =new ImageIcon("Graphies/window/rect.png").getImage();
	private  int expY;
	private  int expX;
	private static int POINT_X;
	private static final int POINT_BIT=5;
	private static final int RMLINE_Y=IMG_RMLINE.getHeight(null)+(PADDING<<1);
	protected static final int RECT_H=IMG_RECT.getHeight(null);
	private static final int RECT_W=IMG_RECT.getWidth(null);
	/**
	 * 数字图片260*36
	 */
	private static final Image IMG_NUMBER =new ImageIcon("Graphies/string/num.png").getImage();
	protected static final int IMG_NUMBER_W=IMG_NUMBER.getWidth(null)/10;
	private static final int IMG_NUMBER_H=IMG_NUMBER.getHeight(null);
	/**
	 * 窗口左上角x坐标
	 */
	protected final int x;
	/**
	 * 窗口左上角y坐标
	 */
	protected final int y;
	/**
	 * 窗口宽度
	 */
	protected final int w;
	/**
	 * 窗口高度
	 */
	protected final int h;
	/**
	 * 游戏数据
	 */
	protected GameDto dto=null;
	protected Layer(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	/**
	 * 绘制窗口
	 */
	protected void createWindow(Graphics g) {
		//左上↖
		g.drawImage(WINDOW_IMG, x, y, x+SIZE, y+SIZE, 0, 0, SIZE, SIZE, null);
		//中上↑
		g.drawImage(WINDOW_IMG, x+SIZE, y, x+w-SIZE, y+SIZE, SIZE, 0, WINDOW_W-SIZE, SIZE, null);
		//右上↗
		g.drawImage(WINDOW_IMG, x+w-SIZE, y, x+w, y+SIZE, WINDOW_W-SIZE, 0, WINDOW_W, SIZE, null);
		//左中←
		g.drawImage(WINDOW_IMG, x, y+SIZE, x+SIZE, y+h-SIZE, 0, SIZE, SIZE, WINDOW_H-SIZE, null);
		//中中□
		g.drawImage(WINDOW_IMG, x+SIZE, y+SIZE, x+w-SIZE, y+h-SIZE, SIZE, SIZE, WINDOW_W-SIZE, WINDOW_H-SIZE,  null);
		//右中→
		g.drawImage(WINDOW_IMG, x+w-SIZE, y+SIZE, x+w, y+h-SIZE, WINDOW_W-SIZE, SIZE, WINDOW_W, WINDOW_H-SIZE,  null);
		//左下↙
		g.drawImage(WINDOW_IMG, x, y+h-SIZE, x+SIZE, y+h, 0, WINDOW_H-SIZE, SIZE, WINDOW_H,  null);
		//中下↓
		g.drawImage(WINDOW_IMG, x+SIZE, y+h-SIZE, x+w-SIZE, y+h, SIZE, WINDOW_H-SIZE, WINDOW_H-SIZE, WINDOW_H,  null);
		//右下↘
		g.drawImage(WINDOW_IMG, x+w-SIZE, y+h-SIZE, x+w, y+h, WINDOW_W-SIZE, WINDOW_H-SIZE, WINDOW_W, WINDOW_H,  null);
	}
	/**
	 * 刷新游戏具体内容
	 * @author user
	 * @param g 画笔
	 */
	abstract public void paint(Graphics g) ;
	public void setDto(GameDto dto) {
		this.dto = dto;
	}
	/**
	 * 显示数字
	 * @param x 左上角x坐标
	 * @param y 左上角y坐标
	 * @param num 要显示的数字
	 * @param bitCount 显示数字位数
	 * @param g 画笔
	 */
	protected void drawNumberLeftPad(int x,int y,int num,int bitCount,Graphics g) {
		//把数字num中的每一位都取出来
		String strNum= Integer.toString(num);
		for (int i = 0; i < bitCount; i++) {
			if(bitCount-i<=strNum.length()) {
				int idx=i+strNum.length()-bitCount;
				int bit =strNum.charAt(idx)-'0';
				g.drawImage(IMG_NUMBER,
						this.x+x+IMG_NUMBER_W*i, this.y+y, this.x+x+IMG_NUMBER_W*(i+1), this.y+y+IMG_NUMBER_H,
						bit*IMG_NUMBER_W, 0, (bit+1)*IMG_NUMBER_W, IMG_NUMBER_H, null);
			}
		}
	}
	/**
	 * 绘制值槽
	 * @param title
	 * @param number
	 * @param value
	 * @param maxValue
	 * @param g
	 */
	protected void drawRect(int Y,String title,String number,double value ,double maxValue ,Graphics g) {
		POINT_X=this.w-IMG_NUMBER_W*POINT_BIT-PADDING;
		//初始经验值显示Y坐标
		maxValue=value>=maxValue?value+0.1:maxValue;
		this.expY=RMLINE_Y-16+IMG_RMLINE.getHeight(null)+PADDING;
		this.expX=this.w-(PADDING>>1);
		g.setColor(Color.BLACK);
		g.fillRect(this.x+PADDING, this.y+expY+32+Y, expX-20, 32);
		g.setColor(Color.white);
		g.fillRect(this.x+PADDING+1, this.y+expY+32+1+Y, expX-20-2, 32-2);
		g.setColor(Color.black);
		g.fillRect(this.x+PADDING+2, this.y+expY+32+2+Y, expX-20-4, 32-4);
		g.setColor(Color.BLUE);
		int w=(int)(value/maxValue*(expX-20-4));
		//g.fillRect(this.x+PADDING+2, this.y+expY+32+2, w, 32-4);
		int subIdx=(int) (value/maxValue*RECT_W);
		g.drawImage(IMG_RECT,
				this.x+PADDING+2, this.y+expY+32+2+Y,
				this.x+PADDING+2+w, this.y+expY+32+2+RECT_H+Y,
				subIdx, 0, subIdx+1, RECT_H, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("黑体", Font.BOLD, 30));
		g.drawString(title, this.x+PADDING+4, this.y+expY+32+26+Y);
		if(number!=null) {
			g.drawString(number, this.x+PADDING+4+290-number.length()*15, this.y+expY+32+26+Y);
		}
	}
}
