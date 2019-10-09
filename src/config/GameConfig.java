package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {
	private int width;
	private int height;
	private int padding;
	private int windowSize;
	private int windowUP;
	private String title;
	private List<LayerConfig> layersConfig;
	public GameConfig() throws Exception {
		SAXReader read=new SAXReader();
		Document doc= read.read("config/cfg.xml");
		Element game= doc.getRootElement();
		this.setUiConfig(game.element("frame"));
		this.setSytemConfig(game.element("system"));
		this.setDataConfig(game.element("data"));
	}
	/**
	 * ≈‰÷√¥∞ø⁄
	 * @param frame ≈‰÷√Œƒº˛¥∞ø⁄≈‰÷√‘™Àÿ
	 */
	private void setUiConfig(Element frame) {
		this.title=frame.attributeValue("title");
		this.width=Integer.parseInt(frame.attributeValue("width"));
		this.height=Integer.parseInt(frame.attributeValue("height"));
		this.padding=Integer.parseInt(frame.attributeValue("padding"));
		this.windowSize=Integer.parseInt(frame.attributeValue("windowSize"));
		this.windowUP=Integer.parseInt(frame.attributeValue("windowUP"));
		List<Element> layers= frame.elements("layer");
		layersConfig =new ArrayList<LayerConfig>();
		for(Element layer:layers) {
			LayerConfig lc=new LayerConfig(
					layer.attributeValue("className"),
					Integer.parseInt(layer.attributeValue("x")),
					Integer.parseInt(layer.attributeValue("y")),
					Integer.parseInt(layer.attributeValue("w")),
					Integer.parseInt(layer.attributeValue("h"))
					);
			layersConfig.add(lc);
		}
	}
	private void setSytemConfig(Element system) {
		
	}
	private void setDataConfig(Element data) {
		
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getPadding() {
		return padding;
	}
	public int getWindowSize() {
		return windowSize;
	}
	public List<LayerConfig> getLayersConfig() {
		return layersConfig;
	}
	public String getTitle() {
		return title;
	}
	public int getWindowUP() {
		return windowUP;
	}
}
