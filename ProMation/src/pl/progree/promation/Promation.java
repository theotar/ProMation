package pl.progree.promation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import pl.progree.promation.debug.Tester;
import pl.progree.promation.kks.ListaKKS;
import pl.progree.promation.kks.StringKKS;
import pl.progree.promation.system.Modul;
import pl.progree.promation.system.Modul.Kanal;
import pl.progree.promation.system.SzafaSystemowa;
import pl.progree.promation.system.SzafaSystemowa.Slot;
import pl.progree.promation.system.SzafaSystemowaFactory;
import pl.progree.promation.templates.SzafaSystemowaTemplate;
import pl.progree.promation.templates.SzafaSystemowaTemplateFactory;

public class Promation {
	private Collection<SzafaSystemowaTemplate> szablonySzafSystemowych=new ArrayList<SzafaSystemowaTemplate>();
	private ListaKKS<Sygnal> listaSygnalow=new ListaKKS<Sygnal>();
	private ListaKKS<SzafaSystemowa> listaSzafSystemowych=new ListaKKS<SzafaSystemowa>();
	private ListaKKS<Modul> listaModulow=new ListaKKS<Modul>();
	
	public Promation(){
		this.prepareTemplates();
		this.loadTestData();
	}
	public void prepareTemplates(){
		this.szablonySzafSystemowych=new ArrayList<SzafaSystemowaTemplate>();
		this.szablonySzafSystemowych.addAll(SzafaSystemowaTemplateFactory.getDefaultTemplates());
	}
	
	
	public Collection<SzafaSystemowaTemplate> getSzablonySzafSystemowych() {
		return szablonySzafSystemowych;
	}
	public void setSzablonySzafSystemowych(
			Collection<SzafaSystemowaTemplate> szablonySzafSystemowych) {
		this.szablonySzafSystemowych = szablonySzafSystemowych;
	}
	public ListaKKS<Sygnal> getListaSygnalow() {
		return listaSygnalow;
	}
	public void setListaSygnalow(ListaKKS<Sygnal> listaSygnalow) {
		this.listaSygnalow = listaSygnalow;
	}
	public ListaKKS<SzafaSystemowa> getListaSzafSystemowych() {
		return listaSzafSystemowych;
	}
	public void setListaSzafSystemowych(
			ListaKKS<SzafaSystemowa> listaSzafSystemowych) {
		this.listaSzafSystemowych = listaSzafSystemowych;
	}
	public ListaKKS<Modul> getListaModulow() {
		return listaModulow;
	}
	public void setListaModulow(ListaKKS<Modul> listaModulow) {
		this.listaModulow = listaModulow;
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
	public boolean addSzafaSystemowa(SzafaSystemowa szafa){
		return this.listaSzafSystemowych.add(szafa);
	}
	public boolean addModul(Modul modul){
		return this.listaModulow.add(modul);
	}
	public boolean addSygnal(Sygnal sygnal){
		return this.listaSygnalow.add(sygnal);
	}
	public boolean alokujModul(Modul modul,Slot slot){
		if(modul.getMiejsceAlokacji()!=null) return false;
		if(slot.getZaalokowanyModul()!=null) return false;
		modul.setMiejsceAlokacji(slot);
		slot.setZaalokowanyModul(modul);
		return true;
	}
	public boolean alokujSygnal(Sygnal sygnal,Kanal kanal){
		if(sygnal.getMiejsceAlokacji()!=null) return false;
		if(kanal.getZaalokowano()!=null) return false;
		sygnal.setMiejsceAlokacji(kanal);
		kanal.setZaalokowano(sygnal);
		return true;
	}

	public static void main(String[] args) {
		
		
		Promation promation=new Promation();
		promation.listaSygnalow.sortASC();
//		Iterator<Sygnal> itr=promation.listaSygnalow.iterator();
//		while(itr.hasNext()){
//			itr.next().info();
//		}
		Tester test=new Tester();
		test.start();
		
		
		
		
	}

}
