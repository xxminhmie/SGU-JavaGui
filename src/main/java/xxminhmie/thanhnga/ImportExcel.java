package xxminhmie.thanhnga;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



//import com.mysql.cj.result.Row;

import xxminhmie.sgu.javagui.gui.common.AbstractButton;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.model.SkuModel;

public class ImportExcel extends AbstractButton{
	public ImportExcel(int x, int y) {
		super(x, y);
		setNameBtn("Import Excel");
	}

	public ImportExcel(int x, int y, int w, int h) {
		super(x, y, w, h);
		setNameBtn("Import Excel");
		
		init();
	}
	
	/*
	 * Thanh Nga
	 */
	
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
    
    public static Map<ProductModel,SkuModel> readExcel(String excelFilePath) throws IOException {
        
        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
 
        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);
 
        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);
 
        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        //mP
        Map<ProductModel,SkuModel> map = new HashMap<ProductModel, SkuModel>();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
 
            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();
 
            // Read cells and set value for book object
            ProductModel product = new ProductModel();
            SkuModel    sku   	 = new SkuModel();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell =cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                case COLUMN_INDEX_ID:
                	product.setId((long) new BigDecimal((double) cellValue).intValue());
                    break;
                case COLUMN_INDEX_NAME:
                	product.setName((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_BRAND:
                	product.setBrand((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_DESCRIPTION:
                	product.setDescription((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_COLOR:
                	sku.setColor((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_SIZE:
                	sku.setSize((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_QUANTITY:
                	sku.setQuantity((int)  new BigDecimal((double) cellValue).intValue());
                    break;
                case COLUMN_INDEX_PRICE:
                	sku.setPrice((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_IMPORTPRICE:
                	sku.setImportPrice((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_IMAGE:
                	sku.setImage((String) getCellValue(cell));
                    break;
                case COLUMN_INDEX_STATUS:
                	sku.setStatus((String) getCellValue(cell));
                    break;    
                default:
                    break;
                }
 
            }
            map.put(product,sku);
//            list_pro.add(product);
        }
 
        workbook.close();
        inputStream.close();

        return map;
    }
    
 // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
 
    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }
 
        return cellValue;
    
    
    }
    
}
