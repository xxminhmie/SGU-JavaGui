package xxminhmie.sgu.javagui.gui.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

public class DeleteButton extends AbstractButton{
	public DeleteButton(int x, int y) {
		super(x, y);
		setNameBtn("Delete");	
		init();

	}
	public DeleteButton(int x, int y, int w, int h) {
		super(x, y, w, h);
		setNameBtn("Delete");	
		init();

	}

}
