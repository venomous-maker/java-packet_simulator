package sim.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CNApp extends JFrame {

	private ContentPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CNApp frame = new CNApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CNApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 300);
		contentPane = new ContentPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setBounds(775, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(678, 11, 89, 23);
		contentPane.add(btnStart);
		
		JLabel lblNewLabel = new JLabel("0.000 msec");
		lblNewLabel.setBounds(10, 204, 282, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPacketsDropped = new JLabel("0 packets dropped out of 0");
		lblPacketsDropped.setBounds(10, 229, 282, 14);
		contentPane.add(lblPacketsDropped);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"300 packets/s", "500 packets/s"}));
		comboBox.setBounds(166, 11, 151, 22);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"300 packets/s", "500 packets/s", "800 packets/s"}));
		comboBox_1.setBounds(517, 11, 151, 22);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("Emission Rate");
		lblNewLabel_1.setBounds(42, 15, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Transmission Rate");
		lblNewLabel_1_1.setBounds(393, 15, 114, 14);
		contentPane.add(lblNewLabel_1_1);
		
		btnStart.addActionListener(contentPane);
		btnNewButton.addActionListener(contentPane);
		
		contentPane.startButton = btnStart;
		contentPane.stopButton = btnNewButton;
		contentPane.microSecond = lblNewLabel;
		contentPane.packetData = lblPacketsDropped;
		contentPane.genCombo = comboBox;
		contentPane.transCombo = comboBox_1;
		
	}

}
