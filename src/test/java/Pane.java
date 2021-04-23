import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Pane extends JFrame{
	
	JScrollPane pane;
	JPanel panel;
	JButton[] btn = new JButton[10];
	
	public Pane() {
		this.setSize(160,200);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(160,200));
		this.add(panel);
		
		pane =  new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		for(int i=0; i< this.btn.length; i++) {
			btn[i] = new JButton("Text");
		}
	}
	
	public static void main(String[] args) {
		Pane p =new Pane();
		p.setVisible(true);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
