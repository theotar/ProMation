/**
 * 
 */
package pl.progree.promation.kks;

/**
 * @author Progree
 *
 */
public class StringKKS extends KodKKS{
	private String rdzen;
	private String rozszerzenie;
	
	public StringKKS(String rdzen) {
		super();
		this.rdzen = rdzen;
		this.rozszerzenie=null;
	}

	public StringKKS(String rdzen, String rozszerzenie) {
		super();
		this.rdzen = rdzen;
		this.rozszerzenie = rozszerzenie;
	}
	@Override
	public String getRdzen() {
		return rdzen;
	}

	public void setRdzen(String rdzen) {
		this.rdzen = rdzen;
	}
	@Override
	public String getRozszerzenie() {
		return rozszerzenie;
	}

	public void setRozszerzenie(String rozszerzenie) {
		this.rozszerzenie = rozszerzenie;
	}

	
	
	
	
	
	
}
