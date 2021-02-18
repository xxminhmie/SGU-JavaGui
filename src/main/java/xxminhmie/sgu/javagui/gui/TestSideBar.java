package xxminhmie.sgu.javagui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestSideBar {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1200,700));
		frame.setLocationRelativeTo(null); // this will center your application
		frame.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.black);
		panel.setPreferredSize(new Dimension(160,700));
		
		JButton btn1 = new JButton("Button 1");
		btn1.setPreferredSize(new Dimension(90,40));
		JButton btn2 = new JButton("Button 2");
		btn2.setPreferredSize(new Dimension(90,40));

		JPanel content1 = new JPanel();
		panel.add(btn1);
		panel.add(btn2);
		frame.add(panel,BorderLayout.WEST);
		frame.setVisible(true);
	}

}
