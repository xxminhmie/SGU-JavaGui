package xxminhmie.sgu.javagui.gui.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xxminhmie.sgu.javagui.model.BillDetailModel;
import xxminhmie.sgu.javagui.model.BillModel;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.model.ReportModel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.service.impl.BillDetailService;
import xxminhmie.sgu.javagui.service.impl.BillService;
import xxminhmie.sgu.javagui.service.impl.ProductService;
import xxminhmie.sgu.javagui.service.impl.SkuService;

public class ThongKeTheoDoanhThu {

	public static ReportModel tinhMonth(int month, int year, Long productId, ReportModel reportModel) {
		int quantity = 0;
		int price = 0;
		SkuService skuService = new SkuService();
		List<SkuModel> skuList = skuService.findByProductId(productId);

		BillDetailService detailService = new BillDetailService();
		List<BillDetailModel> detailList = detailService.findAll();

		for (SkuModel sku : skuList) {
			for (BillDetailModel detail : detailList) {
				if (sku.getId().toString().equals(detail.getSkuId().toString())) {
					BillService billService = new BillService();
					BillModel model = billService.findOne(detail.getBillId());
					String date = String.valueOf(model.getCreatedDate());
//					System.out.println(String.valueOf(BirthdayCheck.getMonth(date))); 
//					System.out.println(String.valueOf(BirthdayCheck.getYear(date))); 
					if (String.valueOf(BirthdayCheck.getMonth(date)).equals(String.valueOf(month))&&String.valueOf(BirthdayCheck.getYear(date)).equals(String.valueOf(year))) {
						quantity += detail.getQuantity();
						price += Integer.parseInt(detail.getSubTotal());
					}
				}
			}
		}

		reportModel.setProductId(productId);
		reportModel.setTotal(String.valueOf(price));
		reportModel.setQuantity(quantity);
	
		return reportModel;

	}
	
	public static void tinhQuy(int quy, int nam, Long productId, ReportModel reportModel) {
//		tinhMonth(4, 2021, productId, map);
//		tinhMonth(5, 2021, productId, map);
//		tinhMonth(6, 2021, productId, map);

	}
	public static void tinhYear(int nam, Long productId, Map<Integer,String> map) {
//		tinhQuy(1, 2021, productId, map);
//		tinhQuy(2, 2021, productId, map);
//		tinhQuy(3, 2021, productId, map);
//		tinhQuy(4, 2021, productId, map);

	}

	public static void main(String[] args) {
		ProductService service = new ProductService();
		List<ProductModel> list = service.findAll();
		
		HashMap<Integer, String> map = new HashMap<Integer, String>();
//		for(ProductModel e : list) {
////			tinhMonth(4, 2021, e.getId(),map);
//			tinhQuy(2,2021,e.getId(),map);
//		}
//		tinhQuy(2,2021,1L,map);

		
		System.out.print(map);
	}

}
