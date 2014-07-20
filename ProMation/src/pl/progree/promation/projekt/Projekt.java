/**
 * 
 */
package pl.progree.promation.projekt;

import java.io.Serializable;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public class Projekt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String nazwa;
	/**
	 * @param nazwa
	 */
	public Projekt(String nazwa) {
		super();
		this.nazwa = nazwa;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	

}
