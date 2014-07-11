package pl.progree.promation.templates;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SzafaSystemowaTemplate {
	private String oznaczenie;
	private Set<String> nazwySlotow;
	
	public SzafaSystemowaTemplate(String oznaczenie) {
		super();
		this.oznaczenie = oznaczenie;
		this.nazwySlotow=new TreeSet<String>();
	}
	@Override
	public String toString() {
		return this.oznaczenie;
	}
	public String getOznaczenie() {
		return oznaczenie;
	}

	public void setOznaczenie(String oznaczenie) {
		this.oznaczenie = oznaczenie;
	}

	public Set<String> getNazwySlotow() {
		return nazwySlotow;
	}

	public void setNazwySlotow(Set<String> nazwySlotow) {
		this.nazwySlotow = nazwySlotow;
	}
	
	public void info(){
		System.out.println("Nazwa szablonu: " +this.oznaczenie);
		System.out.print("Sloty:");
		Iterator<String> itr=this.nazwySlotow.iterator();
		while(itr.hasNext()) System.err.print(itr.next()+", ");
		System.out.println();
	}
	
	
}
