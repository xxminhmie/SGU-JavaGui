package xxminhmie.thanhnga;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import xxminhmie.sgu.javagui.gui.common.AbstractButton;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.model.SkuModel;

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
	 * Thanh Nga
	 */
	public void run() {
	}

	public static final int COLUMN_INDEX_ID           = 0;
    public static final int COLUMN_INDEX_NAME         = 1;
    public static final int COLUMN_INDEX_BRAND        = 2;
    public static final int COLUMN_INDEX_DESCRIPTION  = 3;
    public static final int COLUMN_INDEX_COLOR  	  = 4;
    public static final int COLUMN_INDEX_SIZE  		  = 5;
    public static final int COLUMN_INDEX_QUANTITY  	  = 6;
    public static final int COLUMN_INDEX_PRICE  	  = 7;
    public static final int COLUMN_INDEX_IMPORTPRICE  = 8;
    public static final int COLUMN_INDEX_IMAGE  	  = 9;
    public static final int COLUMN_INDEX_STATUS  	  = 10;
    
    private static CellStyle cellStyleFormatNumber = null;
    
//    List<ProductModel> list_pro;
//    List<SkuModel> list_sku;
//    list = list_pro + list_sku;
    
    //Write data into Excel
    public static void writeExcel(List<ProductModel> pro, List<SkuModel> sku, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
 
        // Create sheet
        Sheet sheet = workbook.createSheet("Books"); // Create sheet with sheet name

        int rowIndex = 0;
         
        // Write header
        writeHeader(sheet, rowIndex);
 
        // Write data
        rowIndex++;
        for (ProductModel product : pro) {
            for(SkuModel Sku: sku) {
            	// Create row
                Row row = sheet.createRow(rowIndex);
                // Write data on row
                write(product, Sku, row);
                rowIndex++;
            }
        }
         
        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
 
        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
    
	// Write data
    private static void write(ProductModel pro, SkuModel sku, Row row) {
        if (cellStyleFormatNumber == null) {
        	
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
             
            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
         
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(pro.getId());
        cell.setCellStyle(cellStyleFormatNumber);
 
        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(pro.getName());
 
        cell = row.createCell(COLUMN_INDEX_BRAND);
        cell.setCellValue(pro.getBrand());
        
        cell = row.createCell(COLUMN_INDEX_DESCRIPTION);
        cell.setCellValue(pro.getDescription());
        
        cell = row.createCell(COLUMN_INDEX_COLOR);
        cell.setCellValue(sku.getColor());
        
        cell = row.createCell(COLUMN_INDEX_SIZE);
        cell.setCellValue(sku.getSize());
        
        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellValue(sku.getQuantity());
        cell.setCellStyle(cellStyleFormatNumber);
        
        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellValue(sku.getPrice());
        
        cell = row.createCell(COLUMN_INDEX_IMPORTPRICE);
        cell.setCellValue(sku.getImportPrice());
        
        cell = row.createCell(COLUMN_INDEX_IMAGE);
        cell.setCellValue(sku.getImage());
        
        cell = row.createCell(COLUMN_INDEX_STATUS);
        cell.setCellValue(sku.getStatus());
    }
    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;
 
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
	
	//Write header
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);
         
        // Create row
        Row row = sheet.createRow(rowIndex);
         
        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");
 
        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Name");
 
        cell = row.createCell(COLUMN_INDEX_BRAND);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Brand");
 
        cell = row.createCell(COLUMN_INDEX_DESCRIPTION);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Description");
        
        cell = row.createCell(COLUMN_INDEX_COLOR);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Color");
        
        cell = row.createCell(COLUMN_INDEX_SIZE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Size");
        
        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quantity");
        
        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Price");
        
        cell = row.createCell(COLUMN_INDEX_IMPORTPRICE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Import Price");
        
        cell = row.createCell(COLUMN_INDEX_IMAGE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Image");
        
        cell = row.createCell(COLUMN_INDEX_STATUS);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Status");
 
    }
	
    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
     
    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
    
}
