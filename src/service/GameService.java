package service;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import org.dom4j.Document;

import dto.GameDto;
import dto.Player;
import entity.GameAct;

public class GameService {
	private GameDto dto;
	/**
	 * 随机数生成器
	 */
	private Random random=new Random();
	/**
	 * 方块种类数
	 */
	private static final int MAX_TYPE=7; 
	public GameService(GameDto dto) {
		this.dto=dto;
//		GameAct act=new GameAct(random.nextInt(MAX_TYPE));
//		dto.setGameAct(act);
	}
	/**
	 * 控制器按键上↑
	 */
	public void keyUp() {
		if(!dto.isStart()) {
			return;
		}
		this.dto.getGameAct().round(this.dto.getGameMap());
	}
	/**
	 * 控制器按键下↓
	 */
	public boolean keyDown() {
		if(!dto.isStart()) {
			return false;
		}
		if(this.dto.getGameAct().move(0, 1,this.dto.getGameMap())) {
			return false;
		}
		//获得游戏地图对象
		boolean[][] map= this.dto.getGameMap();
		Point[] act=this.dto.getGameAct().getActPoints();
		//将方块堆积到地图数组中
		for(int i=0;i<act.length;i++) {
			map[act[i].x][act[i].y]=true;
		}
		//判断是否可以消除行,并计算获得经验值
		int exp= this.plusEXP();
		//增加经验值,加分
		this.levleUp(exp);
		//刷新一个新的方块
		this.dto.getGameAct().init(this.dto.getNext());
		//随机生成下一个方块
		this.dto.setNext(random.nextInt(MAX_TYPE));
		//判断游戏是否结束
		if(this.isLose()) {
			this.afterLose();
		}
		return true;
	}
	/**
	 * 游戏失败后的处理
	 */
	private void afterLose() {
		//游戏重新进入开始前状态
		this.dto.setStart(false);
	}
	/**
	 * 判断游戏是否失败
	 */
	private boolean isLose() {
		//获得现在的活动方块
		Point [] points=this.dto.getGameAct().getActPoints();
		//获得现在的游戏地图
		boolean [][] map=this.dto.getGameMap();
		//判断新刷新的活动块是否与地图重合
		for (int i = 0; i < points.length; i++) {
			if(map[points[i].x][points[i].y]) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 升级操作
	 * @param exp
	 */
	private void levleUp(int exp) {
		int level=this.dto.getLevel();
		int rmLine=this.dto.getNowRemoveLine();
		int point=this.dto.getNowPoint();
		rmLine+=exp;
		if(exp==1) {
			point+=20;
		}
		if(exp==2) {
			point+=45;
		}
		if(exp==3) {
			point+=75;
		}
		if(exp==4) {
			point+=110;
		}
		level=rmLine/20;
		this.dto.setNowRemoveLine(rmLine);
		this.dto.setNowPoint(point);
		this.dto.setLevel(level);
	}
	/**
	 * 消行操作,返回消除行数
	 */
	private int plusEXP() {
		//获得游戏地图
		boolean map[][]= this.dto.getGameMap();
		int exp=0;
		//扫描游戏地图判断是否有可以消除的行
		for(int y=0;y<map[0].length;y++) {
			//判断是否可以消行
			if(isCanRemoveLine(y, map)) {
				//如果可以消行,就消除
				this.removeLine(y,map);
				exp++;
			}
		}
		return exp;
	}
	/**
	 * 消行处理
	 * @param y
	 * @param map
	 */
	private void removeLine(int RowNum, boolean[][] map) {
		for (int x = 0; x < map.length; x++) {
			for(int y=RowNum;y>0;y--) {
				map[x][y]=map[x][y-1];
			}
			map[x][0]=false;
		}
	}
	/**
	 * 判断一行是否可消除
	 * @param y
	 * @return
	 */
	private boolean isCanRemoveLine(int y,boolean[][] map) {
		//单行内对每一个单元格扫描
		for(int x=0;x<map.length;x++) {
			if(!map[x][y]) {
				//如果有一个方格为false则直接跳到下一行
				return false;
			}
		}
		return true;
	}
	/**
	 * 控制器按键左←
	 */
	public void keyLeft() {
		if(!dto.isStart()) {
			return;
		}
		this.dto.getGameAct().move(-1, 0,this.dto.getGameMap());
	}
	/**
	 * 控制器按键右→
	 */
	public void keyRight() {
		if(!dto.isStart()) {
			return;
		}
		this.dto.getGameAct().move(1, 0,this.dto.getGameMap());
	}
	public void levelUp() {
		int point=this.dto.getNowPoint();
		int rmline=this.dto.getNowRemoveLine();
		int lv=this.dto.getLevel();
		int speed=this.dto.getSpeed();
		point+=10;
		rmline+=1;
		if(rmline%20==0) {
			lv+=1;
			if(lv==1) {
				this.dto.setSpeed(speed-100);
			}
			else if(lv>21) {
				this.dto.setSpeed(100);
			}
			else if(lv>1&&lv<=21){
				this.dto.setSpeed(speed-5*lv);
			}
		}
		this.dto.setNowPoint(point);
		this.dto.setNowRemoveLine(rmline);
		this.dto.setLevel(lv);
	}
	public void setRecodeDataBase(List<Player> players) {
		this.dto.setDbRecode(players);
	}
	public void setRecodeDisk(List<Player> players) {
		this.dto.setDiskRecode(players);
	}
	/**
	 * 启动主线程,开始游戏
	 */
	public void startMainThread() {
		dto.setCheat(false);
		GameAct act=new GameAct(random.nextInt(MAX_TYPE));
		dto.setGameAct(act);
		boolean map[][]=new boolean[10][18];
		dto.setGameMap(map);
		dto.setLevel(0);
		dto.setNowPoint(0);
		dto.setNowRemoveLine(0);
		dto.setNext(random.nextInt(MAX_TYPE));
		dto.setStart(true);
	}
	/**
	 * 下落到最底
	 */
	public void down() {
		if(this.dto.isStart())
		while(!keyDown());
	}
	/**
	 * 阴影
	 */
	public void shadow() {
		this.dto.setShadow(!this.dto.isShadow());
	}
	/**
	 * 暂停
	 */
	public void pause() {
		this.dto.setPause(!this.dto.isPause());
	}
}
