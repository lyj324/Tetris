package ui;

import javax.swing.JFrame;

import config.ConfigFactory;
import config.GameConfig;

public class JFrameGame extends JFrame{
	public JFrameGame(JPanelGame panelGame) {
		//������ö���
		GameConfig cfg=ConfigFactory.getGameConfig(); 
		//���ñ���
		this.setTitle(cfg.getTitle());
		//���Ĭ�Ϲر�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô��ڴ�С
		this.setSize(cfg.getWidth(), cfg.getHeight());
		//�������û��ı䴰�ڴ�С
		this.setResizable(false);
		//���þ���
		this.setLocationRelativeTo(null);
		this.setLocation((int)this.getLocation().getX(), (int)this.getLocation().getY()-cfg.getWindowUP());
		//���Ĭ��Panel
		this.setContentPane(panelGame);
		//Ĭ�ϸô���Ϊ��ʾ
		this.setVisible(true);
	}
}
