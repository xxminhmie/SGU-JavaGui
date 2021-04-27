package xxminhmie.sgu.javagui.pdf;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import xxminhmie.sgu.javagui.gui.common.MoneyFormat;
import xxminhmie.sgu.javagui.model.BillDetailModel;
import xxminhmie.sgu.javagui.model.BillModel;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.model.StaffModel;
import xxminhmie.sgu.javagui.service.impl.BillDetailService;
import xxminhmie.sgu.javagui.service.impl.BillService;
import xxminhmie.sgu.javagui.service.impl.ProductService;
import xxminhmie.sgu.javagui.service.impl.SkuService;
import xxminhmie.sgu.javagui.service.impl.StaffService;

public class PDFExporter {
	public static void export(Long id) {
		Document document = new Document();
		try {
			BillService service = new BillService();
			BillModel billModel = service.findOne(id);

			// title
			Font font = new Font(FontFamily.HELVETICA, 24.0f, Font.NORMAL, BaseColor.BLACK);
			Chunk titleChunk = new Chunk("Invoice", font);
			Paragraph titlePara = new Paragraph();
			titlePara.add(titleChunk);
			titlePara.setAlignment(Element.ALIGN_CENTER);

			// address
			Font font1 = new Font(FontFamily.HELVETICA, 12.0f);
			Chunk addressChunk = new Chunk("23 Hong Bang Street, Ward 1, District 11, Ho Chi Minh City", font1);
			Paragraph addressPara = new Paragraph();
			addressPara.add(addressChunk);
			addressPara.setAlignment(Element.ALIGN_CENTER);

			// table: left: date, staff, right: invoice id
			PdfPTable headerTable = new PdfPTable(2);
			headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			headerTable.setWidthPercentage(80);

			Font font2 = new Font(FontFamily.HELVETICA, 12.0f);

			PdfPCell headerCell;
			headerCell = new PdfPCell(new Phrase("Date: "+billModel.getCreatedDate().toString(), font2));
			headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			headerCell.setBorder(Rectangle.NO_BORDER);
			headerTable.addCell(headerCell);

			headerCell = new PdfPCell(new Phrase("HD ID: "+billModel.getId(), font2));
			headerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			headerCell.setBorder(Rectangle.NO_BORDER);
			headerTable.addCell(headerCell);

			StaffService staffService = new StaffService();
			StaffModel staffModel = staffService.findOne(billModel.getStaffId());
			
			headerCell = new PdfPCell(new Phrase("Staff name: "+staffModel.getLastName(), font2));
			headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			headerCell.setBorder(Rectangle.NO_BORDER);
			headerTable.addCell(headerCell);

			headerCell = new PdfPCell(new Phrase("", font2));
			headerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			headerCell.setBorder(Rectangle.NO_BORDER);
			headerTable.addCell(headerCell);

			// line
			LineSeparator line = new LineSeparator();
			line.setPercentage(80);
			line.setLineColor(new BaseColor(179, 179, 179));
			Chunk linebreak = new Chunk(line);

			// table: product detail
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(80);
			table.setWidths(new int[] { 1, 4, 1, 2 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA, 12.0f);

			PdfPCell hcell;

			hcell = new PdfPCell(new Phrase("SKU ID", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setBorder(Rectangle.NO_BORDER);
			hcell.setMinimumHeight(24.0f);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setBorder(Rectangle.NO_BORDER);
			hcell.setMinimumHeight(24.0f);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Qty", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setBorder(Rectangle.NO_BORDER);
			hcell.setMinimumHeight(24.0f);
			table.addCell(hcell);


			hcell = new PdfPCell(new Phrase("Price", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			hcell.setBorder(Rectangle.NO_BORDER);
			hcell.setMinimumHeight(24.0f);
			table.addCell(hcell);

			Font elementFont = FontFactory.getFont(FontFactory.HELVETICA, 10.0f);
			
			
			BillDetailService detailService = new BillDetailService();
			List<BillDetailModel> list = detailService.findListByBillId(id);
			
            for (BillDetailModel news : list) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(news.getSkuId().toString(),elementFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setMinimumHeight(16.0f);
                table.addCell(cell);

                SkuService skuService = new SkuService();
                SkuModel skuModel = skuService.findOne(news.getSkuId());
                
                ProductService productService = new ProductService();
                ProductModel productModel= productService.findOne(skuModel.getProductId());
                
                cell = new PdfPCell(new Phrase(productModel.getName(),elementFont));
//                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setMinimumHeight(16.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(news.getQuantity()),elementFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingRight(5);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setMinimumHeight(16.0f);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(MoneyFormat.customFormat(Long.parseLong(news.getSubTotal())),elementFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setMinimumHeight(16.0f);
                cell.setPaddingRight(5);
                table.addCell(cell);
                
           
            }
			// table: left: label (subtotal, discount, total) right: price
			PdfPTable totalTable = new PdfPTable(2);
			totalTable.setWidthPercentage(80);
			totalTable.setWidths(new int[] { 6, 2 });

			PdfPCell totalCell;

//			totalCell = new PdfPCell(new Phrase("Subtotal: ", headFont));
//			totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//			totalCell.setBorder(Rectangle.NO_BORDER);
//			totalCell.setMinimumHeight(24.0f);
//			totalTable.addCell(totalCell);
//			totalCell = new PdfPCell(new Phrase(MoneyFormat.customFormat(), headFont));
//			totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//			totalCell.setBorder(Rectangle.NO_BORDER);
//			totalCell.setMinimumHeight(24.0f);
//			totalTable.addCell(totalCell);

//			totalCell = new PdfPCell(new Phrase("DiscountID: ", headFont));
//			totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//			totalCell.setBorder(Rectangle.NO_BORDER);
//			totalCell.setMinimumHeight(24.0f);
//			totalTable.addCell(totalCell);
//			totalCell = new PdfPCell(new Phrase(detail, headFont));
//			totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//			totalCell.setBorder(Rectangle.NO_BORDER);
//			totalCell.setMinimumHeight(24.0f);
//			totalTable.addCell(totalCell);

			totalCell = new PdfPCell(new Phrase("Total: ", new Font(FontFamily.HELVETICA, 14.0f, Font.BOLD)));
			totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			totalCell.setBorder(Rectangle.NO_BORDER);
			totalCell.setMinimumHeight(24.0f);
			totalTable.addCell(totalCell);
			totalCell = new PdfPCell(new Phrase(MoneyFormat.customFormat(Long.parseLong(billModel.getTotal())), headFont));
			totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			totalCell.setBorder(Rectangle.NO_BORDER);
			totalCell.setMinimumHeight(24.0f);
			totalTable.addCell(totalCell);

			PdfWriter.getInstance(document, new FileOutputStream("Bill.pdf"));
			document.open();
//            document.add(phrase);

			document.add(titlePara);
			document.add(addressPara);
			document.add(headerTable);
			document.add(linebreak);
			document.add(table);
			document.add(linebreak);
			document.add(totalTable);

			document.close();

		} catch (DocumentException ex) {

		} catch (FileNotFoundException e) {
		}

	}

	public static void main(String[] args) {
		PDFExporter.export(1L);
		if (Desktop.isDesktopSupported()) {

			try {
				File myFile = new File("HelloWorld.pdf");
				Desktop.getDesktop().open(myFile);
			} catch (IOException ex) {
				// no application registered for PDFs
			}

		}
	}

}
