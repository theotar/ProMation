/**
 * 
 */
package pl.progree.promation.system;

import java.util.Iterator;

import pl.progree.promation.kks.KodKKS;
import pl.progree.promation.templates.SzafaSystemowaTemplate;

/**
 * @author Progree
 *
 */
public abstract class SzafaSystemowaFactory {
	public static SzafaSystemowa create(KodKKS kks,SzafaSystemowaTemplate szablon){
		if(kks == null || kks.toString().isEmpty()) return null;
		SzafaSystemowa szafa=new SzafaSystemowa(kks);
		if(szablon.getNazwySlotow() == null) return szafa;
		Iterator<String> itr=szablon.getNazwySlotow().iterator();
		while(itr.hasNext()) szafa.addSlot(itr.next());
		return szafa;
	}
}
