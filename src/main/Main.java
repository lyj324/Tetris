package main;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import control.GameControl;
import control.PlayerControl;
import dto.GameDto;
import service.GameService;
import ui.JFrameGame;
import ui.JPanelGame;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//创建游戏数据源
		GameDto dto=new GameDto();
		//创建游戏面板
		JPanelGame panel=new JPanelGame(dto);
		//创建游戏逻辑块(安装游戏数据源)
		GameService service=new GameService(dto);
		//创建游戏控制器(连接游戏面板与游戏逻辑块)
		GameControl gameControl=new GameControl(panel, service,dto);
		//创建玩家控制器(连接游戏控制器)
		PlayerControl control=new PlayerControl(gameControl);
		//创建游戏窗口(安装游戏面板)
		new JFrameGame(panel);
		//安装玩家控制器
		panel.setGameControl(control);
	}
}
