package dao;

import java.util.List;

import dto.Player;

/**
 * ���ݳ־ò�ӿ�
 * @author user
 *
 */
public interface Data {
	/**
	 * �������
	 * @return
	 */
	public List<Player> loadData();
	/**
	 * ��������
	 * @param players
	 */

	public void saveDate(int nowPoint, boolean cheat);
	
}
