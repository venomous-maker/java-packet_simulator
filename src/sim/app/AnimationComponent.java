package sim.app;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


public class AnimationComponent {
	
	ArrayList<Packet> Inserted = new ArrayList<>();
	ArrayList<Packet> InTransmit = new ArrayList<>();
	ArrayList<Packet> Dropped = new ArrayList<>();
	ArrayList<Packet> Removed = new ArrayList<>();
	
	int inLimit = 320;
	int ExitLimit = 810;
	Random random = new  Random();
	ArrayList<Channel> Buffer = new ArrayList<>();
	int totalPkt = 0;
	int droppedPkt = 0;
	
	public void generateNewPkt() {
		totalPkt +=1;
		Packet packet = new Packet();
		packet.setCordinates(85,102);
		packet.setWidthHeight(15,17);
		packet.setColor(new Color(random.nextFloat(),
				random.nextFloat(),
				random.nextFloat()));
		Inserted.add(packet);
		Dropped.clear();
		
	}
	public void resetData() {
		Inserted.clear();
		InTransmit.clear();
		Dropped.clear();
		Removed.clear();
		this.createDefaultBuffer();
		totalPkt = 0;
		droppedPkt = 0;
	}
	public void createDefaultBuffer() {
		Buffer.clear();
		int xint = 80;
		for(int i=0;i<13;i++) {
			if(i==0) {
				// make long channel
				Channel ch = new Channel();
				ch.setCordinates(xint, 100);
				ch.setWidthHeight(240, 20);
				ch.setColor(Color.white);
				Buffer.add(ch);
				xint +=240;
				
			}
			else if(i==12) {
				// make last long channel
				Channel ch = new Channel();
				ch.setCordinates(570, 100);
				ch.setWidthHeight(240, 20);
				ch.setColor(Color.white);
				Buffer.add(ch);
			}
			else if(i==11) {
				// make the square channel
				Channel ch = new Channel();
				ch.setCordinates(520, 85);
				ch.setWidthHeight(50, 50);
				ch.setColor(Color.white);
				Buffer.add(ch);
			}
			else {
				Channel ch = new Channel();
				ch.setCordinates(xint, 60);
				ch.setWidthHeight(20, 100);
				ch.setColor(Color.white);
				Buffer.add(ch);
				xint+=20;
				
			}
		}
	}
	
	public AnimationComponent() {
		// TODO Auto-generated constructor stub
	
	
	}
	
	public void animateIn() {
		// here increase the value of the packts which are Inserted
		ArrayList<Packet> tmp = new ArrayList<>();
		ArrayList<Packet> tmp1 = new ArrayList<>();
		for(Packet p:Inserted) {
			p.x += 1;
			if(p.x>=this.inLimit-20) {
				if(InTransmit.size()<11) {
//					System.out.println("Size:"+InTransmit.size());
					InTransmit.add(p);
				}else {
					droppedPkt +=1;
					Dropped.add(p);
				}
			}
			else {
				tmp.add(p);
			}
		}
		Inserted.clear();
		Inserted = new ArrayList<>(tmp);
		// animate the removed
		for(Packet p:Removed) {
			p.x += 1;
			if(p.x<this.ExitLimit-1) {
				tmp1.add(p);
			}
			
		}
		Removed.clear();
		Removed = new ArrayList<>(tmp1);
	}
	
	public void animateChannel() {
		int i = 0;
		while(i<InTransmit.size()) {
			Color color = InTransmit.get(i).color;
			int j = 11-i;
			Buffer.get(j).color = color;
			i++;
		}
		while(i < 11) {
			Buffer.get(11-i).color = Color.white;
			i++;
		}	
	}
	
	public void emitPackets() {
		ArrayList<Packet> tmp = new  ArrayList<>();
		if(InTransmit.size()>0) {
			InTransmit.get(0).x = 570;
			Removed.add(InTransmit.get(0));
			for(int i=1;i<InTransmit.size();i++) {
				tmp.add(InTransmit.get(i));
			}
		}
		InTransmit.clear();
		InTransmit = new ArrayList<>(tmp);
	}
	
	public void drawPackets(Graphics g) {
		// Here all the packets will be draw packets which are going in or going exit
		for(Packet p:Inserted) {
			p.paintObj(g);
		}
		for(Packet p:Dropped) {
			p.y = p.y + 1;
			p.paintObj(g);
		}
		for(Packet p:Removed) {
			p.paintObj(g);
		}
	}
	
	public void drawChannel(Graphics g) {
		for(Channel b:Buffer) {
			b.paintObj(g);
		}
	}
	
	
	

}
