package xxminhmie.sgu.javagui.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Demo {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(new Dimension(200,200));
		JPanel p = new JPanel();
		JTextField tf = new JTextField(10);
		f.add(p);
		p.add(tf);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}

}
