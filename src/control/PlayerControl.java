package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerControl extends KeyAdapter{
	private GameControl gameControl;
	public PlayerControl(GameControl gameControl) {
		this.gameControl=gameControl;
	}
	/**
	 * 键盘按下事件
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode()+":"+e.getKeyChar());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			this.gameControl.keyUp();
			break;
		case KeyEvent.VK_DOWN:
			this.gameControl.keyDown();
			break;
		case KeyEvent.VK_LEFT:
			this.gameControl.keyLeft();
	break;
		case KeyEvent.VK_RIGHT:
			this.gameControl.keyRight();
	break;
		case KeyEvent.VK_W:
			this.gameControl.keyUp();
			break;
		case KeyEvent.VK_S:
			this.gameControl.keyDown();
			break;
		case KeyEvent.VK_A:
			this.gameControl.keyLeft();
	break;
		case KeyEvent.VK_D:
			this.gameControl.keyRight();
	break;
		case KeyEvent.VK_8:
			this.gameControl.keyUp();
			break;
		case KeyEvent.VK_2:
			this.gameControl.keyDown();
			break;
		case KeyEvent.VK_4:
			this.gameControl.keyLeft();
	break;
		case KeyEvent.VK_6:
			this.gameControl.keyRight();
	break;
	/**
	 * 作弊按键
	 */
		case KeyEvent.VK_C:
			this.gameControl.levelUp();
	break;
	/**
	 * 瞬间下落
	 */
		case KeyEvent.VK_K:
			this.gameControl.down();
	break;
		case KeyEvent.VK_5:
			this.gameControl.down();
	break;
	/**
	 * 关闭阴影
	 */
		case KeyEvent.VK_J:
			this.gameControl.shadow();
	break;
	/**
	 * 暂停开关
	 */
		case KeyEvent.VK_P:
			this.gameControl.pause();
	break;
	/**
	 * Ai测试
	 */
		case KeyEvent.VK_H:
			this.gameControl.ai();
	break;
		case KeyEvent.VK_0:
			this.gameControl.ai();
	break;
		case KeyEvent.VK_SPACE:
			this.gameControl.doAi();
	break;
		case KeyEvent.VK_U:
			this.gameControl.openAi();
	break;
		default:
			break;
		}
	}
}
