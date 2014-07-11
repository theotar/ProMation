/**
 * 
 */
package pl.progree.promation.kks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Progree
 *
 */
public class ListaKKS<E extends hasKKS> extends ArrayList<E> {
	public static abstract class Selector<T>{
		public abstract boolean Select(T obiektDoPorownania);	
	}
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
	public E find(String KKS){
		for (E element : this) {
			if(element.getKKS().toString().equals(KKS)) return element;
		}
		return null;
	}
	
	public ListaKKS<E> subList(Selector<E> selector) {
		ListaKKS<E> lista=new ListaKKS<E>();
		for (E e : this) {
			if(selector.Select(e)) lista.add(e);
		}
		return lista;
	}
	

}
