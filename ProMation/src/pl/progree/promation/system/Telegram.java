/**
 * 
 */
package pl.progree.promation.system;

import pl.progree.promation.kks.KodKKS;
import pl.progree.promation.kks.ListaKKS;
import pl.progree.promation.kks.StringKKS;
import pl.progree.promation.kks.hasKKS;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public class Telegram implements hasKKS,AlokowalnyWModule{
	private class Sygnal extends pl.progree.promation.system.Sygnal{
		private class InnerKKS extends StringKKS{

			public InnerKKS(String rozszerzenie) {
				this.setRozszerzenie(rozszerzenie);
			}
			@Override
			public String getRdzen() {
				return Telegram.this.getKKS().getRdzen().toString();
			}
				
		}
		private String opisSygnalu;
		public Sygnal(String rozszerzenieKKS) {
			super();
			this.setKodKKS(new Telegram.Sygnal.InnerKKS(rozszerzenieKKS));
		}
		public String getOpisSygnalu() {
			return opisSygnalu;
		}
		public void setOpisSygnalu(String opisSygnalu) {
			this.opisSygnalu = opisSygnalu;
		}
		@Override
		public String getOpis() {
			return Telegram.this.getOpis()+" "+this.getOpisSygnalu();
		}
		@Override
		public void setOpis(String opis) {
			Telegram.this.setOpis(opis);
		}
	}
	private KodKKS kodKKS;
	private String opis;
	private ListaKKS<pl.progree.promation.system.Sygnal> listaSygnalow;
	
	

	public Telegram(KodKKS kodKKS) {
		super();
		this.kodKKS = kodKKS;
		this.listaSygnalow=new ListaKKS<pl.progree.promation.system.Sygnal>();
	}
	
	public boolean dodajSygnal(String rozszerzenieKKS,String opisSygnalu){
		Telegram.Sygnal sygnal=new Telegram.Sygnal(rozszerzenieKKS);
		sygnal.setOpisSygnalu(opisSygnalu);
		return this.listaSygnalow.add(sygnal);
	}

	public KodKKS getKodKKS() {
		return kodKKS;
	}



	public void setKodKKS(KodKKS kodKKS) {
		this.kodKKS = kodKKS;
	}



	public String getOpis() {
		return opis;
	}



	public void setOpis(String opis) {
		this.opis = opis;
	}



	public ListaKKS<pl.progree.promation.system.Sygnal> getListaSygnalow() {
		return listaSygnalow;
	}



	public void setListaSygnalow(
			ListaKKS<pl.progree.promation.system.Sygnal> listaSygnalow) {
		this.listaSygnalow = listaSygnalow;
	}



	@Override
	public KodKKS getKKS() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		return this.getKKS().toString();
	}

}
