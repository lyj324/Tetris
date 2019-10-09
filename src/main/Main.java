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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//������Ϸ����Դ
		GameDto dto=new GameDto();
		//������Ϸ���
		JPanelGame panel=new JPanelGame(dto);
		//������Ϸ�߼���(��װ��Ϸ����Դ)
		GameService service=new GameService(dto);
		//������Ϸ������(������Ϸ�������Ϸ�߼���)
		GameControl gameControl=new GameControl(panel, service,dto);
		//������ҿ�����(������Ϸ������)
		PlayerControl control=new PlayerControl(gameControl);
		//������Ϸ����(��װ��Ϸ���)
		new JFrameGame(panel);
		//��װ��ҿ�����
		panel.setGameControl(control);
	}
}
