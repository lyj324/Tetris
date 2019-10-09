package dto;

import java.util.List;

import entity.GameAct;

public class GameDto {
	private List<Player> dbRecode;
	private List<Player> diskRecode;
	private boolean[][] gameMap;
	private GameAct gameAct;
	private int next;
	private int nowLevel;
	private int nowPoint;
	private int nowRemoveLine;
	private boolean start;
	private boolean shadow;
	private boolean pause;
	private int speed;
	private boolean cheat;
	private boolean bgm;
	private boolean ai;
	/**
	 * 构造函数
	 */
	public GameDto() {
		dtoInit();
	}
	/**
	 * dto初始化
	 */
	public void dtoInit() {
		this.gameMap=new boolean[10][18];
	}
	
	public List<Player> getDbRecode() {
		return dbRecode;
	}
	public void setDbRecode(List<Player> dbRecode) {
		this.dbRecode = dbRecode;
	}
	public List<Player> getDiskRecode() {
		return diskRecode;
	}
	public void setDiskRecode(List<Player> diskRecode) {
		this.diskRecode = diskRecode;
	}
	public boolean[][] getGameMap() {
		return gameMap;
	}
	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}
	public GameAct getGameAct() {
		return gameAct;
	}
	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getLevel() {
		return nowLevel;
	}
	public void setLevel(int nowLevel) {
		this.nowLevel = nowLevel;
	}
	public int getNowPoint() {
		return nowPoint;
	}
	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}
	public int getNowRemoveLine() {
		return nowRemoveLine;
	}
	public void setNowRemoveLine(int nowRemoveLine) {
		this.nowRemoveLine = nowRemoveLine;
	}
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public boolean isShadow() {
		return shadow;
	}
	public void setShadow(boolean shadow) {
		this.shadow = shadow;
	}
	public boolean isPause() {
		return pause;
	}
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isCheat() {
		return cheat;
	}
	public void setCheat(boolean cheat) {
		this.cheat = cheat;
	}
	public boolean isBgm() {
		return bgm;
	}
	public void setBgm(boolean bgm) {
		this.bgm = bgm;
	}
	public boolean isAi() {
		return ai;
	}
	public void setAi(boolean ai) {
		this.ai = ai;
	}
}
