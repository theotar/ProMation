/**
 * 
 */
package pl.progree.promation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public abstract class ExcelImporter {
	public enum OpcjeImportuSygnalow{
		
	}
	public static ArrayList<Sygnal> importujSygnaly(
			String sciezkaDoPliku,
			int arkusz,
			int rzad,
			int kolumnaRdzenia,
			int kolumnaRozszerzenia,
			int kolumnaOpisu) throws IOException{
		InputStream inputStream=null;
		if(sciezkaDoPliku=="debug") 
			inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/sygnaly.xls");
		else inputStream=new FileInputStream(new File(sciezkaDoPliku));
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheetAt(arkusz);
		ArrayList<Sygnal> listaSygnalow=new ArrayList<Sygnal>();
		Row row=sheet.getRow(rzad);
		
		while(!row.getCell(kolumnaRdzenia).getStringCellValue().isEmpty()){
			System.out.println(row.getCell(kolumnaRdzenia).getStringCellValue());
			listaSygnalow.add(SygnalFactory.getSygnal(
					row.getCell(kolumnaRdzenia).getStringCellValue(), 
					row.getCell(kolumnaRozszerzenia).getStringCellValue(),
					row.getCell(kolumnaOpisu).getStringCellValue()));
			row=sheet.getRow(++rzad);
			if(row==null) break;
		}
		return listaSygnalow;
	
	}
}
