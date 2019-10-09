package dto;

public class Player {
	private String name;
	private int point;
	private String time;
	private boolean cheat;
	public Player() {super();}
	public Player(String name, int point, String time, boolean cheat) {
		super();
		this.name = name;
		this.point = point;
		this.time = time;
		this.cheat = cheat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isCheat() {
		return cheat;
	}
	public void setCheat(boolean cheat) {
		this.cheat = cheat;
	}
}
