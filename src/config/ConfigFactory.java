package config;

public class ConfigFactory {
	private static GameConfig GAME_CONFIG=null; 
	static {
		try {
			GAME_CONFIG=new GameConfig();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static GameConfig getGameConfig() {
		return GAME_CONFIG;
	}
}
