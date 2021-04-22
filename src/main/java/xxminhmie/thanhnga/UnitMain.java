package xxminhmie.thanhnga;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

//import com.gpcoder.apachepoi.Book_Data;

import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.model.SkuModel;

public class UnitMain extends JFrame {
<<<<<<< HEAD
	ArrayList<ProductModel> list;
	/*
	 * TODO: THANH NGA
	 */
	String path = "";
=======
	public ArrayList<ProductModel> list;
	public ArrayList<SkuModel> list_sku;
	String path = "D:\\GitHub\\SGU-JavaGui\\Product.xlsx";

	
>>>>>>> refs/remotes/origin/master
	
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
		ProductModel pro1 = new ProductModel((long) 3 ,"Nike ABC", "Nike", "Des 123");
		ProductModel pro2 = new ProductModel((long) 3 , "Nike ABC", "Nike", "Des 123");
		ProductModel pro3 = new ProductModel((long) 3 , "Nike ABC", "Nike", "Des 123");
		ProductModel pro4 = new ProductModel((long) 3 , "Nike ABC", "Nike", "Des 123");

		list.add(pro1);
		list.add(pro2);
		list.add(pro3);
		list.add(pro4);

		list_sku = new ArrayList<SkuModel>();
        SkuModel sku1 = new SkuModel("yellow", "size35", 3, "300.000dong", "299.000dong", "hinh", "con",1L);
        SkuModel sku2 = new SkuModel("yellow", "size35", 3, "300.000dong", "299.000dong", "hinh", "con",1L);
        SkuModel sku3 = new SkuModel("yellow", "size35", 3, "300.000dong", "299.000dong", "hinh", "con",1L);
        SkuModel sku4 = new SkuModel("yellow", "size35", 3, "300.000dong", "299.000dong", "hinh", "con",1L);
		
        list_sku.add(sku1);
        list_sku.add(sku2);
        list_sku.add(sku3);
        list_sku.add(sku4);
		/*
		 * Giao dien
		 */
		ImportExcel ie = new ImportExcel(30, 30, 200, 20);
		add(ie);
		ie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runImportExcel(list,list_sku, path);
			}
		});
		
		ExportExcel ee = new ExportExcel(30, 60, 200, 20);
		add(ee);
		ee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runExportExcel(list,list_sku, path);
			}
		});
		
		ExportPdf ep = new ExportPdf(30, 90, 200, 20);
		add(ep);
		ep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runExportPdf(list,list_sku, path);
			}
		});
		  
	}
	
	/*
	 * TODO: THANH NGA
	 */
	

	
	public void runImportExcel(ArrayList<ProductModel> list, ArrayList<SkuModel> list_sku, String path) {
		final List<ProductModel> product = list;
		final List<SkuModel> Sku = list_sku;
        try {
        	Map<ProductModel,SkuModel> map = ImportExcel.readExcel(path);
        	Set<ProductModel> set = map.keySet();
        	for(ProductModel p:set) {
        		System.out.println(p.getId()+"	" +p.getBrand()+ "	" +p.getName()+ "	" + p.getDescription()+ "		" +map.get(p).getColor()+ "	 "+
        				map.get(p).getSize() +"	 "+ map.get(p).getImportPrice() +"	" +map.get(p).getPrice()+"	" +map.get(p).getStatus());
        		
        	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void runExportExcel(ArrayList<ProductModel> list, ArrayList<SkuModel> list_sku, String path) {
		final List<ProductModel> product = list;
		final List<SkuModel> Sku = list_sku;
        try {
			ExportExcel.writeExcel(product, Sku, path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public void runExportPdf(ArrayList<ProductModel> list, ArrayList<SkuModel> list_sku, String path) {

	}
	
	public static void main(String[] arsg) {
		JFrame f = new UnitMain();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
