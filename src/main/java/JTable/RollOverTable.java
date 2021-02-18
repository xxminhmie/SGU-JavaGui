package JTable;

import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.TableModel;
/*
 * https://community.oracle.com/tech/developers/discussion/1389010/highlight-jtable-rows-on-rollover
 */

public class RollOverTable extends JTable{
	private int rollOverRowIndex = -1;
	
	public RollOverTable(TableModel model) {
		super(model);
		RollOverListener listener = new RollOverListener();
		addMouseMotionListener(listener);
		addMouseListener(listener);
	}
	private class RollOverListener extends MouseInputAdapter{
		public void mouseExited(MouseEvent e) {
			rollOverRowIndex = -1;
			repaint();
		}
		public void mouseMoved(MouseEvent e) {
			int row = rowAtPoint(e.getPoint());
			if(row != rollOverRowIndex) {
				rollOverRowIndex = row;
				repaint();
			}
		}
	}
}
