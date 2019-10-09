package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.Player;

public class DataSaveLoad extends BaseDao<Player> implements Data{

	@Override
	public List<Player> loadData() {
		String sql = "select * from Player order by point DESC limit 5";
		DataSaveLoad data=new DataSaveLoad();
    	List<Player> players = data.selectData(sql,Player.class);
		return players;
	}

	@Override
	public void saveDate(int nowPoint, boolean ischeat) {
		String name=System.getProperty("user.name");
		int point=nowPoint;
		boolean cheat=ischeat;
		Date now = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
	    String time=ft.format(now);
	    String sql = "INSERT INTO player (name, point, time, cheat) VALUES (?,?,?,?)";
	    DataSaveLoad data=new DataSaveLoad();
	    data.executeData(sql, name,point,time,cheat);
	    sql="DELETE FROM Player WHERE NAME = ? && time NOT IN ( SELECT * FROM ( SELECT time FROM Player WHERE NAME = ? ORDER BY point DESC LIMIT 5 ) AS x )";
	    data.executeData(sql, name,name);
	}
}
