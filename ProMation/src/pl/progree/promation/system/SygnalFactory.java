/**
 * 
 */
package pl.progree.promation.system;

import pl.progree.promation.kks.StringKKS;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public abstract class SygnalFactory {
	public static Sygnal create(String rdzenKKS,String rozszerzenieKKS, String opis){
		if(rdzenKKS == null || rdzenKKS.isEmpty()) return null;
		if(rozszerzenieKKS !=null && rozszerzenieKKS.isEmpty() ) rozszerzenieKKS=null;
		if(opis == null) opis="";
		Sygnal sygnal=new Sygnal(new StringKKS(rdzenKKS, rozszerzenieKKS));
		sygnal.setOpis(opis);
		return sygnal;
	}
}
