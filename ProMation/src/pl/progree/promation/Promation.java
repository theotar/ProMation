package pl.progree.promation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import pl.progree.promation.debug.Tester;
import pl.progree.promation.event.PromationListener;
import pl.progree.promation.event.PromationeEvent;
import pl.progree.promation.kks.ListaKKS;
import pl.progree.promation.kks.StringKKS;
import pl.progree.promation.projekt.Projekt;
import pl.progree.promation.system.Modul;
import pl.progree.promation.system.Sygnal;
import pl.progree.promation.system.Modul.Kanal;
import pl.progree.promation.system.SzafaSystemowa;
import pl.progree.promation.system.SzafaSystemowa.Slot;
import pl.progree.promation.system.SzafaSystemowaFactory;
import pl.progree.promation.templates.SzafaSystemowaTemplate;
import pl.progree.promation.templates.SzafaSystemowaTemplateFactory;

public class Promation {
	private Collection<SzafaSystemowaTemplate> szablonySzafSystemowych=new ArrayList<SzafaSystemowaTemplate>();
	private ListaKKS<Sygnal> listaSygnalow=new ListaKKS<Sygnal>();
	//private ListaKKS<SzafaSystemowa> listaSzafSystemowych=new ListaKKS<SzafaSystemowa>();
	private ListaKKS<Modul> listaModulow=new ListaKKS<Modul>();
	private ArrayList<PromationListener> listeners;
	private ArrayList<Projekt>projekty;
	
	public Promation(){
		this.listeners=new ArrayList<PromationListener>();
		this.projekty=new ArrayList<Projekt>();
		this.prepareTemplates();
		this.loadTestData();
	}
	public void prepareTemplates(){
		this.szablonySzafSystemowych=new ArrayList<SzafaSystemowaTemplate>();
		this.szablonySzafSystemowych.addAll(SzafaSystemowaTemplateFactory.getDefaultTemplates());
	}
	protected void fireProjektAddedEvent(Projekt projekt){
		PromationeEvent e=new PromationeEvent(this);
		e.setProjekt(projekt);
		for (PromationListener l : this.listeners) {
			l.projectAdded(e);
		}
	}
	protected void fireSzafaSystemowaAddedEvent(Projekt projekt, SzafaSystemowa szafa){
		PromationeEvent e=new PromationeEvent(this);
		e.setProjekt(projekt);
		e.setSzafaSystemowa(szafa);
		for (PromationListener l : this.listeners) {
			l.szafaSystemowaAdded(e);
		}
	}
	protected void fireModulAddedEvent(Projekt projekt, Modul modul){
		PromationeEvent e=new PromationeEvent(this);
		e.setProjekt(projekt);
		e.setModul(modul);
		for (PromationListener l : this.listeners) {
			l.modulAdded(e);
		}
	}
	public boolean addProjekt(Projekt p){
		boolean isAdded=this.projekty.add(p);
		this.fireProjektAddedEvent(p);
		return isAdded;
	}
	public boolean addSzafaSystemowa(Projekt projekt,SzafaSystemowa szafaSystemowa,boolean fireEvent){
		boolean isAdded=projekt.addSzafaSystemowa(szafaSystemowa);
		if(fireEvent) this.fireSzafaSystemowaAddedEvent(projekt, szafaSystemowa);
		return isAdded;	
	}
	public boolean addSzafaSystemowa(Projekt projekt,SzafaSystemowa szafaSystemowa){
		return this.addSzafaSystemowa(projekt, szafaSystemowa, true);
	}
	public boolean addModul(Projekt projekt,Modul modul,boolean fireEvent){
		boolean isAdded=projekt.addModul(modul);
		if(fireEvent) this.fireModulAddedEvent(projekt, modul);
		return isAdded;	
	}
	public boolean addModul(Projekt projekt,Modul modul){
		return this.addModul(projekt, modul, true);
	}
	public void addListener(PromationListener listener){
		this.listeners.add(listener);
	}
	public void removeListener(PromationListener listener){
		this.listeners.remove(listener);
	}
	public boolean saveProjektToFile(Projekt projekt,File plik){
		if(plik==null || projekt==null) return false;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		boolean ok=true;
		 /*
		  * Zapis do strumienia (plikowego, ale mo¿e byæ dowolne)
		  */
		 try {
		   fos= new FileOutputStream(plik); //utworzenie strumienia wyjœciowego
		   oos = new ObjectOutputStream(fos);  //utworzenie obiektu zapisuj¹cego do strumienia
		 
		   oos.writeObject(projekt); //serializacja obiektu
		 
		 } catch (FileNotFoundException e) {
		   e.printStackTrace();
		   ok=false;
		 } catch (IOException e) {
		   e.printStackTrace();
		   ok=false;
		 } finally {
		   // zamykamy strumienie w finally
		   try {
		     if (oos != null) oos.close();
		   } catch (IOException e) {}
		   try {
		     if (fos != null) fos.close();
		   } catch (IOException e) {}
		 }
		return ok;
	}
	public Projekt loadProjektFromFile(File plik){
		 FileInputStream fis = null;
		 ObjectInputStream ois = null;
		 Projekt projekt=null;
		 boolean ok=true;
		 try {
		   fis = new FileInputStream(plik); //utworzenie strumienia wejœciowego  
		   ois = new ObjectInputStream(fis); //utworzenie obiektu odczytuj¹cego obiekty ze strumienia
		 
		   projekt = (Projekt) ois.readObject(); //deserializacja obiektu
		   projekt.setPlik(plik);
		 
		 } catch (FileNotFoundException e) {
		   e.printStackTrace();
		 } catch (IOException e) {
		   e.printStackTrace();
		 } catch (ClassNotFoundException e) {
		   e.printStackTrace();
		 } finally {
		   // zasoby zwalniamy w finally
		   try {
		     if (ois != null) ois.close();
		   } catch (IOException e) {}
		   try {
		     if (fis != null) fis.close();
		   } catch (IOException e) {}
		 }
		 if(ok == false) return null;
		 if(this.addProjekt(projekt)) return projekt;
		 else return null;	 
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
//	public ListaKKS<SzafaSystemowa> getListaSzafSystemowych() {
//		return listaSzafSystemowych;
//	}
//	public void setListaSzafSystemowych(
//			ListaKKS<SzafaSystemowa> listaSzafSystemowych) {
//		this.listaSzafSystemowych = listaSzafSystemowych;
//	}
	public ListaKKS<Modul> getListaModulow() {
		return listaModulow;
	}
	public void setListaModulow(ListaKKS<Modul> listaModulow) {
		this.listaModulow = listaModulow;
	}
	public ArrayList<Projekt> getProjekty() {
		return (ArrayList<Projekt>) this.projekty.clone();
	}
	public void setProjekty(ArrayList<Projekt> projekty) {
		this.projekty = projekty;
	}
//	public void loadSzafySystemowe(){
//		SzafaSystemowaTemplate szafaMelody=this.szablonySzafSystemowych.iterator().next();
//		for(int i=1;i<4;i++) 
//			this.listaSzafSystemowych.add(SzafaSystemowaFactory.create(new StringKKS(String.format("04CDA%02d", i)), szafaMelody));
//		for(int i=10;i<14;i++) 
//			this.listaSzafSystemowych.add(SzafaSystemowaFactory.create(new StringKKS(String.format("04CDA%02d", i)), szafaMelody));
//		for(int i=20;i<24;i++) 
//			this.listaSzafSystemowych.add(SzafaSystemowaFactory.create(new StringKKS(String.format("04CDA%02d", i)), szafaMelody));
//	}
	public void loadTestData(){
		//this.loadSzafySystemowe();
		ArrayList<Sygnal> lista=null;
		try {
			 lista=ExcelImporter.importujSygnaly("debug", 0, 7, 2, 3, 4);
			 this.listaSygnalow.addAll(lista);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public boolean addSzafaSystemowa(SzafaSystemowa szafa){
//		return this.listaSzafSystemowych.add(szafa);
//	}
	
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
