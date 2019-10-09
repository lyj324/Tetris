package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import ui.Layer;

public class LayerButton extends Layer {
	
	public LayerButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	public void paint(Graphics g) {
		this.createWindow(g);
	}
}
