package dao;

import java.util.List;

import dto.Player;

/**
 * 数据持久层接口
 * @author user
 *
 */
public interface Data {
	/**
	 * 获得数据
	 * @return
	 */
	public List<Player> loadData();
	/**
	 * 储存数据
	 * @param players
	 */

	public void saveDate(int nowPoint, boolean cheat);
	
}
