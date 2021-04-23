package xxminhmie.sgu.javagui.gui.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import xxminhmie.sgu.javagui.gui.ApplicationGUI;

public class SidebarContainer extends JPanel {
	private static final long serialVersionUID = 1L;

	protected static final int SidebarWidth = 160;
	protected static final Color SidebarBg = Color.BLACK;
	protected static final Color SidebarBgHover = new Color(53, 53, 53);
	protected static final Color SidebarFg = Color.WHITE;

	JButton[] listItem;
	AccountPanel accountPanel;
	ActionListener itemListener = new ItemListener();
	JPanel panel = new JPanel();
	JScrollPane pane;

	public SidebarContainer(String[] nameList, String[] namePanel, JPanel content) {
		this.setBackground(SidebarBg);
		this.setPreferredSize(new Dimension(SidebarWidth, ApplicationGUI.FrameHeight));
		this.setLayout(new FlowLayout());

		this.accountPanel = new AccountPanel();
		add(accountPanel);
	
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(200,700));
		panel.setBackground(Color.BLACK);
		
		
		this.listItem = new SidebarItem[nameList.length];
		for (int i = 0; i < nameList.length; i++) {
			this.listItem[i] = new SidebarItem(nameList[i], namePanel[i], content);
			listItem[i].addActionListener(itemListener);
			panel.add(this.listItem[i]);
		}
		
		pane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setPreferredSize(new Dimension(200,700-160));
		pane.setBackground(Color.BLACK);
		pane.getVerticalScrollBar().setBackground(Color.black);
		add(pane);

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
	
	public AccountPanel getAccountPanel() {
		return this.accountPanel;
	}

}
