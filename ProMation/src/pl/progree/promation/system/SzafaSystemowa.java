package pl.progree.promation.system;

import java.io.Serializable;
import java.util.Iterator;

import pl.progree.promation.kks.KodKKS;
import pl.progree.promation.kks.ListaKKS;
import pl.progree.promation.kks.StringKKS;
import pl.progree.promation.kks.hasKKS;

public class SzafaSystemowa implements hasKKS,Serializable{
	public class Slot implements hasKKS,Serializable{
		private KodKKS pelnyKodKKS=new KodKKS(){

			@Override
			public Object getRdzen() {
				return SzafaSystemowa.this.getKKS().getRdzen().toString()+Slot.this.getKKS().getRdzen().toString();
			}
			@Override
			public Object getRozszerzenie() {
				return null;
			}	
		};
		private KodKKS kodKKS;
		private Modul zaalokowanyModul;
		
		protected Slot(String kksSlotu){
			this.kodKKS=new StringKKS(kksSlotu);
		}
		public KodKKS getKodKKS() {
			return kodKKS;
		}
		public void setKodKKS(KodKKS kodKKS) {
			this.kodKKS = kodKKS;
		}
		
		public KodKKS getPelnyKodKKS() {
			return pelnyKodKKS;
		}
		public void setPelnyKodKKS(KodKKS pelnyKodKKS) {
			this.pelnyKodKKS = pelnyKodKKS;
		}
		
		public Modul getZaalokowanyModul() {
			return zaalokowanyModul;
		}
		public void setZaalokowanyModul(Modul zaalokowanyModul) {
			this.zaalokowanyModul = zaalokowanyModul;
		}
		@Override
		public KodKKS getKKS() {		
			return this.getKodKKS();
		}
		@Override
		public String toString() {
			return this.getPelnyKodKKS().toString();
		}
		
	}
	private KodKKS kodKKS;
	private ListaKKS<Slot> sloty=new ListaKKS<Slot>();
	


	public SzafaSystemowa(KodKKS kodKKS) {
		super();
		this.kodKKS = kodKKS;
	}

	public boolean addSlot(String oznaczenie) {
		return sloty.add(new Slot(oznaczenie));
	}

	public KodKKS getKodKKS() {
		return kodKKS;
	}


	public void setKodKKS(KodKKS kodKKS) {
		this.kodKKS = kodKKS;
	}

	/**
	 * 
	 * @return copy of slot list
	 */
	public ListaKKS<Slot> getSloty() {
		return (ListaKKS<Slot>) this.sloty.clone();
	}


	@Override
	public KodKKS getKKS() {
		
		return this.getKodKKS();
	}
	@Override
	public String toString() {
		return this.getKKS().toString();
	}
	
	public void info(){
		System.out.println("KKS: " +this.getKKS().toString());
		System.out.print("Sloty:");
		Iterator<Slot> itr=this.sloty.iterator();
		while(itr.hasNext()) System.err.print(itr.next().getKKS().toString()+", ");
		System.out.println();
	}

}
