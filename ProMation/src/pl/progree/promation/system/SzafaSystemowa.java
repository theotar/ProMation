package pl.progree.promation.system;

import pl.progree.promation.kks.KodKKS;
import pl.progree.promation.kks.ListaKKS;
import pl.progree.promation.kks.StringKKS;
import pl.progree.promation.kks.hasKKS;

public class SzafaSystemowa implements hasKKS{
	protected class Slot implements hasKKS{
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
		@Override
		public KodKKS getKKS() {		
			return this.getKodKKS();
		}
		
	}
	private KodKKS kodKKS;
	private ListaKKS<Slot> listaSlotow=new ListaKKS<SzafaSystemowa.Slot>();
	
	
	public SzafaSystemowa(KodKKS kodKKS) {
		super();
		this.kodKKS = kodKKS;
	}


	public KodKKS getKodKKS() {
		return kodKKS;
	}


	public void setKodKKS(KodKKS kodKKS) {
		this.kodKKS = kodKKS;
	}


	public ListaKKS<Slot> getListaSlotow() {
		return listaSlotow;
	}


	public void setListaSlotow(ListaKKS<Slot> listaSlotow) {
		this.listaSlotow = listaSlotow;
	}


	@Override
	public KodKKS getKKS() {
		
		return this.getKodKKS();
	}
	

}
