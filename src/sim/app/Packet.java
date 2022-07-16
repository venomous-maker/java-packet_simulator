package sim.app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Packet extends Rectangle{
	public Color color;
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setCordinates(int x,int y) {
		this.x = x;
		this.y = y;
	}
	public void setWidthHeight(int width,int height) {
		this.width = width;
		this.height = height;
	}
	public void paintObj(Graphics g) {
		// this will pait the packets
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width, height);
		g.setColor(color);
		g.fillRect(x+2, y+2, width-3, height-3);
	}
}
