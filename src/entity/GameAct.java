package entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameAct {
	/**
	 * 方块数组
	 */
	private Point[] actPoint=null;
	/**
	 * 方块编号
	 */
	private int typeCode;
	private static int MIN_X=0;
	private static int MAX_X=9;
	private static int MIN_Y=0;
	private static int MAX_Y=17;
	private static List<Point[]> TYPE_CONFIG;
	static {
		TYPE_CONFIG=new ArrayList<>(7);
		TYPE_CONFIG.add(new Point[] {new Point(4,0),new Point(3,0),new Point(5,0),new Point(6,0)});
		TYPE_CONFIG.add(new Point[] {new Point(4,0),new Point(3,0),new Point(5,0),new Point(4,1)});
		TYPE_CONFIG.add(new Point[] {new Point(4,0),new Point(3,0),new Point(5,0),new Point(3,1)});
		TYPE_CONFIG.add(new Point[] {new Point(4,0),new Point(3,1),new Point(5,0),new Point(4,1)});
		TYPE_CONFIG.add(new Point[] {new Point(4,0),new Point(4,1),new Point(5,0),new Point(5,1)});
		TYPE_CONFIG.add(new Point[] {new Point(4,0),new Point(3,0),new Point(5,0),new Point(5,1)});
		TYPE_CONFIG.add(new Point[] {new Point(4,0),new Point(3,0),new Point(4,1),new Point(5,1)});
	}
	public GameAct(int typeCode) {
		this.init(typeCode);	
	}
	public void init(int typeCode) {
		this.typeCode=typeCode;
		Point[] points=TYPE_CONFIG.get(typeCode);
		actPoint=new Point[points.length];
		for(int i=0;i<points.length;i++) {
			actPoint[i]=new Point(points[i].x,points[i].y);
		}
	}
	public Point[] getActPoints() {
		return actPoint;
	}
	/**
	 * 方块移动
	 * @param moveX x轴偏移量
	 * @param moveY y轴偏移量
	 */
	public boolean move(int moveX,int moveY,boolean gameMap[][]) {
		//移动处理
		for(int i=0;i<actPoint.length;i++) {
			int newX=actPoint[i].x+moveX;
			int newY=actPoint[i].y+moveY;
			if(this.isOverZone(newX,newY,gameMap)) {
				return false;
			}
		}
		for(int i=0;i<actPoint.length;i++) {
			actPoint[i].x+=moveX;
			actPoint[i].y+=moveY;
		}
		return true;
	}
	/**
	 * 方块旋转
	 * 顺时针
	 * A.x=O.y+O.x-B.y
	 * A.y=O.y-O.x+B.x
	 */
	public void round(boolean gameMap[][]) {
		if(this.typeCode==4) {
			return;
		}
		for(int i=1;i<actPoint.length;i++) {
			int newX= actPoint[0].y+actPoint[0].x-actPoint[i].y;
			int newY= actPoint[0].y-actPoint[0].x+actPoint[i].x;
			if(this.isOverZone(newX,newY,gameMap)) {
				return;
			}
		}
		for(int i=1;i<actPoint.length;i++) {
			int newX= actPoint[0].y+actPoint[0].x-actPoint[i].y;
			int newY= actPoint[0].y-actPoint[0].x+actPoint[i].x;
			actPoint[i].x=newX;
			actPoint[i].y=newY;
		}
	}
	/**
	 * 判断是否超出区域
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isOverZone(int x,int y,boolean[][] gameMap) {
		return x<MIN_X||x>MAX_X||y<MIN_Y||y>MAX_Y||gameMap[x][y];
	}
	/**
	 * 获得方块类型编号
	 * @return
	 */
	public int getTypeCode() {
		return typeCode;
	}
}
