/**
 * 
 */
package pl.progree.promation.projekt;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import pl.progree.promation.kks.ListaKKS;
import pl.progree.promation.system.Modul;
import pl.progree.promation.system.SzafaSystemowa;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public class Projekt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nazwa;
	private transient File plik=new File("E:\\TEMP\\dupa.pro");
	
	protected ListaKKS<SzafaSystemowa> szafySystemowe=new ListaKKS<SzafaSystemowa>();
	protected ListaKKS<Modul> moduly=new ListaKKS<Modul>();
	

	/**
	 * @param nazwa
	 */
	public Projekt(String nazwa) {
		super();
		this.nazwa = nazwa;
		this.szafySystemowe=new ListaKKS<SzafaSystemowa>();
	}
	
	public boolean addSzafaSystemowa(SzafaSystemowa szafa){
		return this.szafySystemowe.add(szafa);
	}
	public boolean addModul(Modul modul){
		return this.moduly.add(modul);
	}
	
	@Override
	public String toString() {
		return this.getNazwa();
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	/**
	 * @return the plik
	 */
	public File getPlik() {
		return plik;
	}

	/**
	 * @param plik the plik to set
	 */
	public void setPlik(File plik) {
		this.plik = plik;
	}

	/**
	 * @return the copy of szafySystemowe
	 */
	public ListaKKS<SzafaSystemowa> getSzafySystemowe() {	
		return  this.szafySystemowe.clone();
	}
	/**
	 * @return the moduly
	 */
	public ListaKKS<Modul> getModuly() {
		return (ListaKKS<Modul>) this.moduly.clone();
	}

}
