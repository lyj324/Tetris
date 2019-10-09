package ui;

import javax.swing.JFrame;

import config.ConfigFactory;
import config.GameConfig;

public class JFrameGame extends JFrame{
	public JFrameGame(JPanelGame panelGame) {
		//获得配置对象
		GameConfig cfg=ConfigFactory.getGameConfig(); 
		//设置标题
		this.setTitle(cfg.getTitle());
		//设计默认关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口大小
		this.setSize(cfg.getWidth(), cfg.getHeight());
		//不允许用户改变窗口大小
		this.setResizable(false);
		//设置居中
		this.setLocationRelativeTo(null);
		this.setLocation((int)this.getLocation().getX(), (int)this.getLocation().getY()-cfg.getWindowUP());
		//设计默认Panel
		this.setContentPane(panelGame);
		//默认该窗口为显示
		this.setVisible(true);
	}
}
