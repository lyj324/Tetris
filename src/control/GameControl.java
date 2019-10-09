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
 * 接受玩家键盘事件
 * 控制画面
 * 控制游戏逻辑
 * @author user
 *
 */
public class GameControl {
	/**
	 * 数据访问接口A
	 */
	private static Data dataA;
	/**
	 * 数据访问接口B
	 */
	private Data dataB;
	/**
	 * 游戏界面层
	 */
	private static JPanelGame panelGame;
	/**
	 * 游戏逻辑层
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
		//从数据接口A获得数据库记录
		this.dataA= new DataSaveLoad();
		//从数据接口B获得数据库记录
		this.dataB= new DataSaveLoad();
		//设置数据库记录到游戏
		this.gameService.setRecodeDataBase(dataA.loadData());
		//设置本地磁盘记录到游戏
		this.gameService.setRecodeDisk(dataB.loadData());
		File dir =new File(System.getProperty("user.dir")+"/Bgm");
		files =dir.listFiles();
		for(File file:files) {
			if(!file.isDirectory());
			bgmCount++;
		}
	}
	/**
	 * 控制器按键上↑
	 */
	public void keyUp() {
		synchronized (this.dto) {
			if(dto.isPause())return;
			this.gameService.keyUp();
			this.panelGame.repaint();
		}
	}
	/**
	 * 控制器按键下↓
	 */
	public void keyDown() {
		synchronized (this.dto) {
		if(dto.isPause())return;
		this.gameService.keyDown();
		this.panelGame.repaint();
		}
	}
	/**
	 * 控制器按键左←
	 */
	public void keyLeft() {
		synchronized (this.dto) {
		if(dto.isPause())return;
		this.gameService.keyLeft();
		this.panelGame.repaint();
		}
	}
	/**
	 * 控制器按键右→
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
	 * 重置游戏
	 */
	private static void afterLose() {
		//保存计分
		dataA.saveDate(dto.getNowPoint(),dto.isCheat());
		//使按钮可以点击
		panelGame.buttonSwitch(true);
		//读取新排名数据
		gameService.setRecodeDataBase(dataA.loadData());
		//关闭BGM
		dto.setBgm(false);
		player.close();
	}
	/**
	 * 游戏开始
	 */
	public static void start() {
		dto.setBgm(true);
		synchronized (dto) {
		dto.setCheat(false);
		panelGame.buttonSwitch(false);
		gameService.startMainThread();
		//创建线程对象
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
					// TODO 自动生成的 catch 块
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
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		};
		//启动线程
		gameThread.start();
		bgmThread.start();
		panelGame.repaint();
		}
	}
	/**
	 * 下落到底
	 */
	public void down() {
		synchronized (this.dto) {
		if(dto.isPause())return;
		this.gameService.down();
		this.panelGame.repaint();
		}
	}
	/**
	 * 阴影控制
	 */
	public void shadow() {
		synchronized (this.dto) {
		if(dto.isPause())return;
		this.gameService.shadow();
		this.panelGame.repaint();
		}
	}
	/**
	 * 暂停
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
						// TODO 自动生成的 catch 块
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
