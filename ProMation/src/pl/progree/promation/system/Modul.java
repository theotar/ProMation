package pl.progree.promation.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import pl.progree.promation.kks.KodKKS;
import pl.progree.promation.kks.hasKKS;
import pl.progree.promation.system.SzafaSystemowa.Slot;

public class Modul implements hasKKS{
	public class Kanal implements Comparable<Kanal>{
		private String oznaczenie;

		protected Kanal(String oznaczenie) {
			super();
			this.oznaczenie = oznaczenie;
		}

		public Modul getModul(){
			return Modul.this;
		}
		public String getOznaczenie() {
			return oznaczenie;
		}

		public void setOznaczenie(String oznaczenie) {
			this.oznaczenie = oznaczenie;
		}

		@Override
		public int compareTo(Kanal o) {
			if(o == null) return 0;
			return this.getOznaczenie().compareTo(o.getOznaczenie());
		}
		
		
	}
	private Slot miejsceAlokacji=null;
	private Collection<Kanal> listaKanalow;
	private String typ;
	
	public Modul(String typ){
		this.typ=typ;
		this.listaKanalow=new TreeSet<Modul.Kanal>();
	}
	public Modul(String typ,int liczbaKanalow){
		this(typ);
		for(int i=0;i<liczbaKanalow;i++)
			this.listaKanalow.add(new Kanal(String.valueOf(i+1)));
	}
	
	public Slot getMiejsceAlokacji() {
		return miejsceAlokacji;
	}
	public void setMiejsceAlokacji(Slot miejsceAlokacji) {
		this.miejsceAlokacji = miejsceAlokacji;
	}
	/**
	 * 
	 * @return copy of channel list
	 */
	public Collection<Kanal> getListaKanalow() {
		ArrayList<Kanal> lista=new ArrayList<Modul.Kanal>();
		lista.addAll(this.listaKanalow);
		return lista;
	}
	public void setListaKanalow(Collection<Kanal> listaKanalow) {
		this.listaKanalow = listaKanalow;
	}
	/**
	 * @return the nazwa
	 */
	public String getTyp() {
		return typ;
	}
	/**
	 * @param nazwa the nazwa to set
	 */
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public void info(){
		System.out.println("Miejsce Alokacji: " + (this.getMiejsceAlokacji() == null ? "Niezaalokowany": this.getMiejsceAlokacji().getPelnyKodKKS()));
		System.out.print("Kanaly:");
		Iterator<Kanal> itr=this.listaKanalow.iterator();
		while(itr.hasNext()) System.err.print(itr.next().getOznaczenie()+", ");
		System.out.println();
	}
	@Override
	public KodKKS getKKS() {
		if(this.getMiejsceAlokacji()==null) return KodKKS.BRAK;
		else return this.getMiejsceAlokacji().getPelnyKodKKS();
	}
	@Override
	public String toString() {
		
		return this.getKKS().toString()+" - "+this.getTyp();
	}
	
}
