package CardLayoutExample;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Sidebar extends JPanel{
	JButton b1;
	JButton b2;
	
	Sidebar(CardLayout card, JPanel content){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(100,50));


		this.b1 = new JButton("Button 1");
		this.b2 = new JButton("Button 2");
		
		
		
		this.customButton(this.b1);
		this.customButton(this.b2);
		
		this.addMyListener(this.b1,card,content,"1");
		this.addMyListener(this.b2,card,content,"2");

		add(b1);
		add(b2);
	}
	public void customButton(JButton b) {
		b.setPreferredSize(new Dimension(100,30));

	}
	public void addMyListener(JButton b,CardLayout card, JPanel content,String name) {
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(content, name);
			}
		});
	}
	
	

}
