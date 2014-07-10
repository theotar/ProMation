/**
 * 
 */
package pl.progree.promation.templates;

import java.util.ArrayList;
import java.util.Collection;

import pl.progree.promation.system.SzafaSystemowa;

/**
 * @author Progree
 *
 */
public abstract class SzafaSystemowaTemplateFactory {
	private static Collection<SzafaSystemowaTemplate> defaultTemplates=null;
	public static Collection<SzafaSystemowaTemplate> getDefaultTemplates(){
		if(SzafaSystemowaTemplateFactory.defaultTemplates != null) return SzafaSystemowaTemplateFactory.defaultTemplates;
		ArrayList<SzafaSystemowaTemplate> szablony=new ArrayList<SzafaSystemowaTemplate>();
		SzafaSystemowaTemplateFactory.defaultTemplates=szablony;
		ArrayList<String> sloty;
		//Klasyczna szafa Melody
		sloty=new ArrayList<String>();
		for(int i=1;i<13;i++){
			sloty.add(String.format("A%02d", i));
			sloty.add(String.format("C%02d", i));
			sloty.add(String.format("E%02d", i));
			sloty.add(String.format("G%02d", i));
		}
		szablony.add(SzafaSystemowaTemplateFactory.create("Szafa Melody(4 kasety po 12 slotów)",sloty));
		return SzafaSystemowaTemplateFactory.defaultTemplates;
	}
	public static SzafaSystemowaTemplate create(String oznaczenie, Collection<String> nazwySlotow){
		if(oznaczenie == null || oznaczenie.isEmpty()) oznaczenie ="Szablon bez nazwy";
		SzafaSystemowaTemplate szafa=new SzafaSystemowaTemplate(oznaczenie);
		if(nazwySlotow != null) szafa.getNazwySlotow().addAll(nazwySlotow);
		return szafa;
	}
}
