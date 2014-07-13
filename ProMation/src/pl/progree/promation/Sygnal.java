/**
 * 
 */
package pl.progree.promation;

import pl.progree.promation.kks.KodKKS;
import pl.progree.promation.kks.hasKKS;
import pl.progree.promation.system.Modul.Kanal;

/**
 * @author Progree
 *
 */
public class Sygnal implements hasKKS{
	private KodKKS kodKKS;
	private String opis;
	private Kanal miejsceAlokacji;

	public Sygnal(KodKKS kodKKS) {
		super();
		this.kodKKS = kodKKS;
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
	
	public Kanal getMiejsceAlokacji() {
		return miejsceAlokacji;
	}

	public void setMiejsceAlokacji(Kanal miejsceAlokacji) {
		this.miejsceAlokacji = miejsceAlokacji;
	}

	public void info(){
		System.out.println("KKS: " + (this.getKodKKS()==null?"BRAK":this.getKodKKS().toString()));
		System.out.println("OPIS: " + this.getOpis());
	}

	@Override
	public KodKKS getKKS() {
		return this.getKodKKS();
	}
}
