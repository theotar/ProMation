package pl.progree.promation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import pl.progree.promation.kks.ListaKKS;
import pl.progree.promation.kks.StringKKS;

public class Promation {
	private ListaKKS listaSygnalow;

	public static void main(String[] args) {
		System.out.println("hello world!");
		StringKKS kks1=new StringKKS("04HLA10AA201");
		System.out.println("kks1:"+kks1);
		StringKKS kks2=new StringKKS("04HLA10AA201","CG01");
		System.out.println("kks2:"+kks2);
		StringKKS kks3=new StringKKS("04HLA10AA201","CG02");
		System.out.println("kks3:"+kks3);
		StringKKS kks4=new StringKKS(null,"CG02");
		System.out.println("kks4:"+kks4);
		StringKKS kks5=new StringKKS("04HLA10AA201");
		System.out.println("kks5:"+kks5);
		StringKKS kks6=new StringKKS("04HLA10AA201","CG01");
		System.out.println("kks6:"+kks6);
		Sygnal s1= new Sygnal(kks2);
		s1.info();
		System.out.println();
		URL fileURL=Thread.currentThread().getContextClassLoader().getResource("testData/sygnaly.xls");
		File file=null;
//		try {
//			file = new FileInputStream(new File("..\\testData\\sygnaly.xls"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
		//Get the workbook instance for XLS file
		HSSFWorkbook workbook=null;
		try {
			workbook = new HSSFWorkbook(Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/sygnaly.xls"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);
		 
		//Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();
		Row row=sheet.getRow(7);
		 
		//Get iterator to all cells of current row
		Iterator<Cell> cellIterator = row.cellIterator();
		System.out.println("ehh:"+cellIterator.next().getStringCellValue());
		Cell cell=row.getCell(2);
		System.out.println("KKS:"+cell.getStringCellValue());
	}

}
