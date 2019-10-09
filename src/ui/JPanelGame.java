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
		//���dto����
		this.dto=dto;
		//��ʼ���齨
		initComponent();
		//��ʼ����
		initLayer();
	}
	/**
	 * ��װ��ҿ�����
	 * @param control
	 */
	public void setGameControl(PlayerControl control) {
		this.addKeyListener(control);
	}
	/**
	 * ��ʼ���齨
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
	 * ���ʼ��
	 */
	private void initLayer() {
		GameConfig cfg=ConfigFactory.getGameConfig();
		List<LayerConfig>layersCfg= cfg.getLayersConfig();
		layers=new ArrayList<Layer>(layersCfg.size());
		try {
			for(LayerConfig layerConfig:layersCfg) {
			//��������
			Class<?> cls =Class.forName(layerConfig.getClassName());
			//��ù��캯��
			Constructor ctr =cls.getConstructor(int.class,int.class,int.class,int.class);
			//���ù��캯����������
			Layer layer=(Layer)ctr.newInstance(
					layerConfig.getX(),layerConfig.getY(),layerConfig.getW(),layerConfig.getH()
					);
			//�������ݶ���
			layer.setDto(this.dto);
			//�Ѵ�����layer������뼯��
			layers.add(layer);
			} 
		}catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		//���û��෽��
		super.paintComponent(g);
		//ѭ��ˢ����Ϸ����
		for (int i=0;i<layers.size();layers.get(i++).paint(g));//ˢ�´���
		//���ؽ���
		this.requestFocus();
	}
	/**
	 * ���ư�ť�Ƿ�ɵ��
	 */
	public void buttonSwitch(boolean onOff) {
		this.START_B.setEnabled(onOff);
	}
}
