package xxminhmie.thanhnga;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import xxminhmie.sgu.javagui.gui.common.AbstractButton;

public class ExportExcel  extends AbstractButton{
	public ExportExcel(int x, int y) {
		super(x, y);
		setNameBtn("Export Excel");
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				run();
			}
			
		});
	}

	public ExportExcel(int x, int y, int w, int h) {
		super(x, y, w, h);
		setNameBtn("Export Excel");
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				run();
			}
			
		});
		init();

	}
	/*
	 * 
	 */
	public void run() {
		
	}


}
