package pl.progree.promation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import pl.progree.promation.kks.ListaKKS;
import pl.progree.promation.kks.StringKKS;
import pl.progree.promation.kks.hasKKS;

public class Promation {
	private ListaKKS listaSygnalow=new ListaKKS();
	
	public Promation(){
		ArrayList<Sygnal> lista=null;
		try {
			 lista=ExcelImporter.importujSygnaly("debug", 0, 7, 2, 3, 4);
			 this.listaSygnalow.addAll(lista);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

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
		
		Promation promation=new Promation();
		Iterator<hasKKS> itr=promation.listaSygnalow.iterator();
		while(itr.hasNext()){
			((Sygnal)itr.next()).info();
		}
		
		
	}

}
