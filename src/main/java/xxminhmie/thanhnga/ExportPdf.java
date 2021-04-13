package xxminhmie.thanhnga;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import xxminhmie.sgu.javagui.gui.common.AbstractButton;

public class ExportPdf  extends AbstractButton{
	public ExportPdf(int x, int y) {
		super(x, y);
		setNameBtn("Export Pdf");
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				run();
			}
			
		});
	}

	public ExportPdf(int x, int y, int w, int h) {
		super(x, y, w, h);
		setNameBtn("Export Pdf");
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
