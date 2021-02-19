package JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ConfirmDialogDemo extends JFrame {
	public ConfirmDialogDemo() {
		setSize(250,100);
        setLocation(500, 300);
        setTitle("JButton Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JLabel label = new JLabel("A JLabel");
        add(label);

        JButton button = new JButton("Click me");
        add(button, "North", 1);
        button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                int click = JOptionPane.showConfirmDialog(null, "This is a confirm dialog");
                if(click==JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Click Yes");
                }
                if (click==JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Click No");
               }
               if (click==JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(null, "Click Cancel");
               }
               if (click==JOptionPane.CLOSED_OPTION) {
                    JOptionPane.showMessageDialog(null, "Click Close");
               }
			}
        	
        });
        setVisible(true);



	}
	 public static void main(String[] args) {
	        ConfirmDialogDemo confirmDialog = new ConfirmDialogDemo();
	    }
	
}
