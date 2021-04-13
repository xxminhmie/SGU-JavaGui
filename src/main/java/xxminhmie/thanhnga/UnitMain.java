package xxminhmie.thanhnga;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import xxminhmie.sgu.javagui.model.ProductModel;

public class UnitMain extends JFrame {
	ArrayList<ProductModel> list;
	String path = "";
	
	/*
	 * Constructor
	 */
	public UnitMain() {
		setSize(500, 500);
		setLayout(null);
		
		
		 /*
		  * Du lieu 
		  */
		list = new ArrayList<ProductModel>(); 
		ProductModel pro1 = new ProductModel(1L,"Nike ABC", "Nike", "Des 123");
		ProductModel pro2 = new ProductModel(1L,"Nike ABC", "Nike", "Des 123");
		ProductModel pro3 = new ProductModel(1L,"Nike ABC", "Nike", "Des 123");
		ProductModel pro4 = new ProductModel(1L,"Nike ABC", "Nike", "Des 123");
		
		list.add(pro1);
		list.add(pro2);
		list.add(pro3);
		list.add(pro4);

		/*
		 * Giao dien
		 */
		ImportExcel ie = new ImportExcel(30, 30, 200, 20);
		add(ie);
		ie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				run(list,path);
			}
		});
	}
	/*
	 * Thanh Nga
	 */
	public void run(ArrayList<ProductModel> list, String path) {

	}

	public static void main(String[] arsg) {
		JFrame f = new UnitMain();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
