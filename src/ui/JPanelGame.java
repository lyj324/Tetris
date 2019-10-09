package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import config.ConfigFactory;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;
import service.GameService;

public class JPanelGame extends JPanel{
	private ArrayList<Layer> layers =null;
	private GameDto dto=null;
	JButton START_B;
	private ImageIcon START=new ImageIcon("Graphies/string/start.png");
	public JPanelGame(GameDto dto) {
		//获得dto对象
		this.dto=dto;
		//初始化组建
		initComponent();
		//初始化层
		initLayer();
	}
	/**
	 * 安装玩家控制器
	 * @param control
	 */
	public void setGameControl(PlayerControl control) {
		this.addKeyListener(control);
	}
	/**
	 * 初始化组建
	 */
	private void initComponent() {
		this.setLayout(null);
		this.START_B=new JButton(START);
		this.START_B.setBounds(855, 54, START.getIconWidth()<<1, START.getIconHeight()<<1);
		this.add(START_B);
		this.START_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameControl.start();
			}
		});
	}
	private void initCom() {}
	/**
	 * 层初始化
	 */
	private void initLayer() {
		GameConfig cfg=ConfigFactory.getGameConfig();
		List<LayerConfig>layersCfg= cfg.getLayersConfig();
		layers=new ArrayList<Layer>(layersCfg.size());
		try {
			for(LayerConfig layerConfig:layersCfg) {
			//获得类对象
			Class<?> cls =Class.forName(layerConfig.getClassName());
			//获得构造函数
			Constructor ctr =cls.getConstructor(int.class,int.class,int.class,int.class);
			//调用构造函数创建对象
			Layer layer=(Layer)ctr.newInstance(
					layerConfig.getX(),layerConfig.getY(),layerConfig.getW(),layerConfig.getH()
					);
			//设置数据对象
			layer.setDto(this.dto);
			//把创建的layer对象放入集合
			layers.add(layer);
			} 
		}catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		//调用基类方法
		super.paintComponent(g);
		//循环刷新游戏画面
		for (int i=0;i<layers.size();layers.get(i++).paint(g));//刷新窗口
		//返回交点
		this.requestFocus();
	}
	/**
	 * 控制按钮是否可点击
	 */
	public void buttonSwitch(boolean onOff) {
		this.START_B.setEnabled(onOff);
	}
}
