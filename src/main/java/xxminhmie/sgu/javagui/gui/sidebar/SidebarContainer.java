package xxminhmie.sgu.javagui.gui.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import xxminhmie.sgu.javagui.gui.ApplicationGUI;

public class SidebarContainer extends JPanel {
	private static final long serialVersionUID = 1L;

	protected static final int SidebarWidth = 160;
	protected static final Color SidebarBg = Color.BLACK;
	protected static final Color SidebarBgHover = new Color(53, 53, 53);
	protected static final Color SidebarFg = Color.WHITE;

	protected JButton[] listItem;
	protected JPanel accountPanel;
	ActionListener itemListener = new ItemListener();

	public SidebarContainer(String[] nameList, String[] namePanel, JPanel content) {
		this.setBackground(SidebarBg);
		this.setPreferredSize(new Dimension(SidebarWidth, ApplicationGUI.FrameHeight));
		this.setLayout(new FlowLayout());

		this.accountPanel = new AccountPanel();
		add(accountPanel);
		this.listItem = new SidebarItem[nameList.length];
		for (int i = 0; i < nameList.length; i++) {
			this.listItem[i] = new SidebarItem(nameList[i], namePanel[i], content);
			listItem[i].addActionListener(itemListener);
			this.add(this.listItem[i]);
		}

	}

	class ItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton src = (JButton) e.getSource();
//			System.out.println(button.getText());
//			ItemListenerHandle(button.getText());
			for(int i=0; i< listItem.length; i++) {
				if(src==listItem[i]) {
//					System.out.print(src.getText());
//					System.out.print(src.getName());
//					src.setBackground(bg);


				}
			}
		}

	}

	public void ItemListenerHandle(String item) {
		for (int i = 0; i < listItem.length; i++) {
			if (listItem[i].getText().equals(item)) {
				this.listItem[i].setBackground(Color.RED);
			} else {
				this.listItem[i].setBackground(Color.BLACK);
			}
		}
	}

}
