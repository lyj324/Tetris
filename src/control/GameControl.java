package control;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javax.swing.ImageIcon;

import ai.PierreDellacherie;
import dao.Data;
import dao.DataBase;
import dao.DataSaveLoad;
import dto.GameDto;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import service.GameService;
import ui.JFrameGame;
import ui.JPanelGame;

/**
 * ������Ҽ����¼�
 * ���ƻ���
 * ������Ϸ�߼�
 * @author user
 *
 */
public class GameControl {
	/**
	 * ���ݷ��ʽӿ�A
	 */
	private static Data dataA;
	/**
	 * ���ݷ��ʽӿ�B
	 */
	private Data dataB;
	/**
	 * ��Ϸ�����
	 */
	private static JPanelGame panelGame;
	/**
	 * ��Ϸ�߼���
	 */
	private static GameService gameService;
	private static Thread gameThread=null;
	private static Thread bgmThread=null;
	private static Thread aiThread=null;
	private static GameDto dto=null;
	private static File files[] =null;
	private static Player player;
	private static int bgmCount;
	public GameControl (JPanelGame jPanelGame,GameService gameService ,GameDto dto) {
		this.dto=dto;
		this.panelGame=jPanelGame;
		this.gameService=gameService;
		//�����ݽӿ�A������ݿ��¼
		this.dataA= new DataSaveLoad();
		//�����ݽӿ�B������ݿ��¼
		this.dataB= new DataSaveLoad();
		//�������ݿ��¼����Ϸ
		this.gameService.setRecodeDataBase(dataA.loadData());
		//���ñ��ش��̼�¼����Ϸ
		this.gameService.setRecodeDisk(dataB.loadData());
		File dir =new File(System.getProperty("user.dir")+"/Bgm");
		files =dir.listFiles();
		for(File file:files) {
			if(!file.isDirectory());
			bgmCount++;
		}
	}
	/**
	 * �����������ϡ�
	 */
	public void keyUp() {
		synchronized (this.dto) {
			if(dto.isPause())return;
			this.gameService.keyUp();
			this.panelGame.repaint();
		}
	}
	/**
	 * �����������¡�
	 */
	public void keyDown() {
		synchronized (this.dto) {
		if(dto.isPause())return;
		this.gameService.keyDown();
		this.panelGame.repaint();
		}
	}
	/**
	 * �������������
	 */
	public void keyLeft() {
		synchronized (this.dto) {
		if(dto.isPause())return;
		this.gameService.keyLeft();
		this.panelGame.repaint();
		}
	}
	/**
	 * �����������ҡ�
	 */
	public void keyRight() {
		synchronized (this.dto) {
		if(dto.isPause())return;
		this.gameService.keyRight();
		this.panelGame.repaint();
		}
	}
	public void levelUp() {
		synchronized (this.dto) {
		dto.setCheat(true);
		this.gameService.levelUp();
		this.panelGame.repaint();
		}
	}
	/**
	 * ������Ϸ
	 */
	private static void afterLose() {
		//����Ʒ�
		dataA.saveDate(dto.getNowPoint(),dto.isCheat());
		//ʹ��ť���Ե��
		panelGame.buttonSwitch(true);
		//��ȡ����������
		gameService.setRecodeDataBase(dataA.loadData());
		//�ر�BGM
		dto.setBgm(false);
		player.close();
	}
	/**
	 * ��Ϸ��ʼ
	 */
	public static void start() {
		dto.setBgm(true);
		synchronized (dto) {
		dto.setCheat(false);
		panelGame.buttonSwitch(false);
		gameService.startMainThread();
		//�����̶߳���
		dto.setSpeed(1300);
		bgmThread= new Thread() {
			public void run() {
				try {
					Thread.sleep(dto.getSpeed());
					Random random=new Random();
					player = new Player(new FileInputStream(files[random.nextInt(bgmCount)].getPath()));
					if(dto.isBgm()&&!player.isComplete()) {
						player.play();
					}
				} catch (InterruptedException | JavaLayerException | FileNotFoundException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}		
			}
		};
		gameThread=new Thread() {
			public void run() {
				while(true) {
					if(!dto.isStart()) {
						afterLose();
						break;
						}
					try {
						Thread.sleep(dto.getSpeed());
						if(dto.isPause()) {
							continue;
						}
						gameService.keyDown();
						panelGame.repaint();
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
			}
		};
		//�����߳�
		gameThread.start();
		bgmThread.start();
		panelGame.repaint();
		}
	}
	/**
	 * ���䵽��
	 */
	public void down() {
		synchronized (this.dto) {
		if(dto.isPause())return;
		this.gameService.down();
		this.panelGame.repaint();
		}
	}
	/**
	 * ��Ӱ����
	 */
	public void shadow() {
		synchronized (this.dto) {
		if(dto.isPause())return;
		this.gameService.shadow();
		this.panelGame.repaint();
		}
	}
	/**
	 * ��ͣ
	 */
	public void pause() {
		synchronized (this.dto) {
		if(!dto.isStart())return;
		this.gameService.pause();
		this.panelGame.repaint();
		}
	}
	public void ai() {
		synchronized (this.dto) {
			if(!dto.isStart())return;
			boolean maps[][]=this.dto.getGameMap();
			this.dto.setCheat(true);
			int type=this.dto.getGameAct().getTypeCode();
			Point[] act= this.dto.getGameAct().getActPoints();
			boolean down=false;
			for(int i=0;i<act.length;i++) {
				if(act[i].y==0) {
					down=true;
				}
			}
			for(int i=0;i<act.length;i++) {
				if(act[i].x==0) {keyRight();keyRight();}
				if(act[i].x==9) {keyLeft();keyLeft();}
			}
			if(down) {down=false;keyDown();keyDown();}
			PierreDellacherie ai =new PierreDellacherie();
			String action[];
			action=ai.getMap(maps,type,down,act).split(",");
			for(int i=0;i<action.length;i++) {
				if(action[i]!="") {
				if(Integer.parseInt(action[i])==4) {
					keyLeft();
				}
				if(Integer.parseInt(action[i])==6) {
					keyRight();
				}
				if(Integer.parseInt(action[i])==8) {
					keyUp();
				}
				if(Integer.parseInt(action[i])==2) {
					keyDown();
				}
				//System.out.print(action[i]+"-");
				}
			}
		}
	}
	public void doAi() {
		ai();
		down();
	}
	public void openAi() {
			synchronized (this.dto) {
		aiThread=new Thread() {
			public void run() {
				while(dto.isAi()) {
					if(!dto.isStart()) {
						break;
						}
					try {
						Thread.sleep(150);
						if(dto.isPause()) {
							continue;
						}
						doAi();
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
			}
		};
	}
			this.dto.setAi(!this.dto.isAi());
			aiThread.start();
	}
}
