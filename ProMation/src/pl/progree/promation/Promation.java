package pl.progree.promation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import pl.progree.promation.kks.ListaKKS;
import pl.progree.promation.kks.StringKKS;
import pl.progree.promation.system.Modul;
import pl.progree.promation.system.SzafaSystemowa;
import pl.progree.promation.system.SzafaSystemowaFactory;
import pl.progree.promation.templates.SzafaSystemowaTemplate;
import pl.progree.promation.templates.SzafaSystemowaTemplateFactory;

public class Promation {
	private Collection<SzafaSystemowaTemplate> szablonySzafSystemowych=new ArrayList<SzafaSystemowaTemplate>();
	private ListaKKS<Sygnal> listaSygnalow=new ListaKKS<Sygnal>();
	private ListaKKS<SzafaSystemowa> listaSzafSystemowych=new ListaKKS<SzafaSystemowa>();
	
	public Promation(){
		this.prepareTemplates();
		this.loadTestData();
	}
	public void prepareTemplates(){
		this.szablonySzafSystemowych=new ArrayList<SzafaSystemowaTemplate>();
		this.szablonySzafSystemowych.addAll(SzafaSystemowaTemplateFactory.getDefaultTemplates());
	}
	public void loadSzafySystemowe(){
		SzafaSystemowaTemplate szafaMelody=this.szablonySzafSystemowych.iterator().next();
		for(int i=1;i<4;i++) 
			this.listaSzafSystemowych.add(SzafaSystemowaFactory.create(new StringKKS(String.format("04CDA%02d", i)), szafaMelody));
		for(int i=10;i<14;i++) 
			this.listaSzafSystemowych.add(SzafaSystemowaFactory.create(new StringKKS(String.format("04CDA%02d", i)), szafaMelody));
		for(int i=20;i<24;i++) 
			this.listaSzafSystemowych.add(SzafaSystemowaFactory.create(new StringKKS(String.format("04CDA%02d", i)), szafaMelody));
	}
	public void loadTestData(){
		this.loadSzafySystemowe();
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
//		Iterator<Sygnal> itr=promation.listaSygnalow.iterator();
//		while(itr.hasNext()){
//			itr.next().info();
//		}
		SzafaSystemowa szafa=promation.listaSzafSystemowych.iterator().next();
		System.out.println(szafa.getListaSlotow().iterator().next().getPelnyKodKKS());
		szafa.getListaSlotow().iterator().next().setZaalokowanyModul(new Modul(32));
		szafa.getListaSlotow().iterator().next().getZaalokowanyModul().info();
		szafa.getListaSlotow().iterator().next().getZaalokowanyModul().setMiejsceAlokacji(szafa.getListaSlotow().iterator().next());
		szafa.getListaSlotow().iterator().next().getZaalokowanyModul().info();
		
		
		
	}

}
