/**
 * 
 */
package pl.progree.promation.kks;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Progree
 *
 */
public class ListaKKS extends ArrayList<hasKKS> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Comparator<hasKKS> komparatorAZ=new Comparator<hasKKS>() {

		@Override
		public int compare(hasKKS o1, hasKKS o2) {
			return o1.getKKS().toString().compareTo(o2.getKKS().toString());
		}
		
	};
	public void sortASC(){
		this.sort(this.komparatorAZ);
	}
	
	

}
