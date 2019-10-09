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
	 * �����������
	 */
	private Random random=new Random();
	/**
	 * ����������
	 */
	private static final int MAX_TYPE=7; 
	public GameService(GameDto dto) {
		this.dto=dto;
//		GameAct act=new GameAct(random.nextInt(MAX_TYPE));
//		dto.setGameAct(act);
	}
	/**
	 * �����������ϡ�
	 */
	public void keyUp() {
		if(!dto.isStart()) {
			return;
		}
		this.dto.getGameAct().round(this.dto.getGameMap());
	}
	/**
	 * �����������¡�
	 */
	public boolean keyDown() {
		if(!dto.isStart()) {
			return false;
		}
		if(this.dto.getGameAct().move(0, 1,this.dto.getGameMap())) {
			return false;
		}
		//�����Ϸ��ͼ����
		boolean[][] map= this.dto.getGameMap();
		Point[] act=this.dto.getGameAct().getActPoints();
		//������ѻ�����ͼ������
		for(int i=0;i<act.length;i++) {
			map[act[i].x][act[i].y]=true;
		}
		//�ж��Ƿ����������,�������þ���ֵ
		int exp= this.plusEXP();
		//���Ӿ���ֵ,�ӷ�
		this.levleUp(exp);
		//ˢ��һ���µķ���
		this.dto.getGameAct().init(this.dto.getNext());
		//���������һ������
		this.dto.setNext(random.nextInt(MAX_TYPE));
		//�ж���Ϸ�Ƿ����
		if(this.isLose()) {
			this.afterLose();
		}
		return true;
	}
	/**
	 * ��Ϸʧ�ܺ�Ĵ���
	 */
	private void afterLose() {
		//��Ϸ���½��뿪ʼǰ״̬
		this.dto.setStart(false);
	}
	/**
	 * �ж���Ϸ�Ƿ�ʧ��
	 */
	private boolean isLose() {
		//������ڵĻ����
		Point [] points=this.dto.getGameAct().getActPoints();
		//������ڵ���Ϸ��ͼ
		boolean [][] map=this.dto.getGameMap();
		//�ж���ˢ�µĻ���Ƿ����ͼ�غ�
		for (int i = 0; i < points.length; i++) {
			if(map[points[i].x][points[i].y]) {
				return true;
			}
		}
		return false;
	}
	/**
	 * ��������
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
	 * ���в���,������������
	 */
	private int plusEXP() {
		//�����Ϸ��ͼ
		boolean map[][]= this.dto.getGameMap();
		int exp=0;
		//ɨ����Ϸ��ͼ�ж��Ƿ��п�����������
		for(int y=0;y<map[0].length;y++) {
			//�ж��Ƿ��������
			if(isCanRemoveLine(y, map)) {
				//�����������,������
				this.removeLine(y,map);
				exp++;
			}
		}
		return exp;
	}
	/**
	 * ���д���
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
	 * �ж�һ���Ƿ������
	 * @param y
	 * @return
	 */
	private boolean isCanRemoveLine(int y,boolean[][] map) {
		//�����ڶ�ÿһ����Ԫ��ɨ��
		for(int x=0;x<map.length;x++) {
			if(!map[x][y]) {
				//�����һ������Ϊfalse��ֱ��������һ��
				return false;
			}
		}
		return true;
	}
	/**
	 * �������������
	 */
	public void keyLeft() {
		if(!dto.isStart()) {
			return;
		}
		this.dto.getGameAct().move(-1, 0,this.dto.getGameMap());
	}
	/**
	 * �����������ҡ�
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
	 * �������߳�,��ʼ��Ϸ
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
	 * ���䵽���
	 */
	public void down() {
		if(this.dto.isStart())
		while(!keyDown());
	}
	/**
	 * ��Ӱ
	 */
	public void shadow() {
		this.dto.setShadow(!this.dto.isShadow());
	}
	/**
	 * ��ͣ
	 */
	public void pause() {
		this.dto.setPause(!this.dto.isPause());
	}
}
