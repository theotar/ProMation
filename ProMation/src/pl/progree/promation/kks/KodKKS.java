/**
 * 
 */
package pl.progree.promation.kks;

import java.io.Serializable;

/**
 * @author Progree
 *
 */
public abstract class KodKKS implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final KodKKS BRAK=new StringKKS("BRAK");
	public static final KodKKS NIEZAALOKOWANY=new StringKKS("niezaalokowany");
	public abstract Object getRdzen();
	public abstract Object getRozszerzenie();
	
	@Override
	public String toString() {
		if(this.getRdzen()==null && this.getRozszerzenie()==null) return "";
		else if(this.getRdzen()==null || this.getRdzen().toString().isEmpty()) return "";
		else if(this.getRozszerzenie() == null || this.getRozszerzenie().toString().isEmpty()) return this.getRdzen().toString();
		return this.getRdzen().toString() + " " +this.getRozszerzenie().toString();
		
	}
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof KodKKS))
			return false;
		return this.toString().equals(obj.toString());
	}
}
