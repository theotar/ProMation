package pl.progree.promation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import pl.progree.promation.kks.ListaKKS;
import pl.progree.promation.kks.StringKKS;

public class Promation {
	private ListaKKS<Sygnal> listaSygnalow=new ListaKKS<Sygnal>();
	
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
		promation.listaSygnalow.sortASC();
		Iterator<Sygnal> itr=promation.listaSygnalow.iterator();
		while(itr.hasNext()){
			itr.next().info();
		}
		
		
	}

}
