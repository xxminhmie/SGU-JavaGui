package xxminhmie.sgu.javagui.gui.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;


public class SaveButton extends JButton{
	public SaveButton() {
		this.setText("Save");
		this.setBackground(new Color(133,177,138));
		this.setSize(new Dimension(100,40));
//		this.setBounds(100, 100, 100, 40);
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        setBackground(new Color(29,170,46));
		        setOpaque(true);
				setBorderPainted(false);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
				setBackground(new Color(133,177,138));

				setOpaque(true);
				setBorderPainted(false);
		    
		    }});

	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1040, 700));
		frame.setLocationRelativeTo(null); // this will center your application
		frame.setLayout(null);

		SaveButton add = new SaveButton();
		DeleteButton del = new DeleteButton();
		ResetButton re = new ResetButton();
		del.setBounds(100, 100, 100, 40);
		re.setBounds(200, 200, 100, 40);
		frame.add(del);
		frame.add(add);
		frame.add(re);
		frame.setVisible(true);
	}


}
