import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ActionerSidebarTest {
	JButton btn1;
	JButton btn2;
	ActionerSidebarTest() {
		this.btn1 = new JButton("1");
		this.btn2 = new JButton("2");
		ActionListener action = new MyClass();
		btn1.addActionListener(action);
		btn2.addActionListener(action);
		JFrame f = new JFrame();
		f.setSize(new Dimension(300, 300));
		f.setLayout(new FlowLayout());
		f.add(btn1);
		f.add(btn2);
		f.setVisible(true);

	}

	public static void main(String[] args) {
		ActionerSidebarTest a = new ActionerSidebarTest();
	}

	class MyClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("1")) {
				JButton button = (JButton) e.getSource();
				button.setText("Clicked");
				btn2.setText("2");
			}else {
				JButton button = (JButton) e.getSource();
				button.setText("Clicked");
				btn1.setText("1");
			}
		}
	}

}
