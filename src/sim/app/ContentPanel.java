package sim.app;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ContentPanel extends JPanel implements ActionListener{
	
	AnimationComponent animationComponent = new  AnimationComponent();
	JLabel microSecond;
	JLabel packetData;
	
	boolean start_flg = false;
	int timerBig = 0;
	
	
	JComboBox genCombo,transCombo;
	
	JButton startButton;
	JButton stopButton;
	
	Timer timer = new Timer(10,this);
	Timer eTimer = new Timer(10,this);
	Timer tTimer = new Timer(10,this);
	
	public ContentPanel() {
		// TODO Auto-generated constructor stub
		animationComponent.createDefaultBuffer();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		// Here all the components will be painted
		animationComponent.drawChannel(g);
		animationComponent.drawPackets(g);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == timer) {
			microSecond.setText(timerBig/1000.0+" msec");
			packetData.setText(animationComponent.droppedPkt+" packets dropped out of "+animationComponent.totalPkt);
			
			animationComponent.animateIn();
			animationComponent.animateChannel();
			timerBig +=10;
		}
		if(e.getSource() == eTimer) {
			
			animationComponent.generateNewPkt();
			
		}
		if(e.getSource() == tTimer) {
			animationComponent.emitPackets();
		}
		
		if(e.getSource() == startButton ) {
			timer.start();
			int i = genCombo.getSelectedIndex();
			int j = transCombo.getSelectedIndex();
			eTimer.setDelay(i==0?3333:2000);
			tTimer.setDelay(j==0?3333:j==1?2000:1250);
			eTimer.start();
			tTimer.start();
			start_flg = true;
		}
		if(e.getSource() == stopButton) {
			timer.stop();
			tTimer.stop();
			eTimer.stop();
			animationComponent.resetData();
			start_flg = false;
		}
		repaint();
		
	}

}
