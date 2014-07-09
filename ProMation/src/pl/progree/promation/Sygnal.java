/**
 * 
 */
package pl.progree.promation;

import pl.progree.promation.kks.KodKKS;

/**
 * @author Progree
 *
 */
public class Sygnal {
	private KodKKS kodKKS;

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
	
	public void info(){
		System.out.println("KKS: " + (this.getKodKKS()==null?"BRAK":this.getKodKKS().toString()));
	}
}
