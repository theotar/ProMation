/**
 * 
 */
package pl.progree.promation.debug;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import javax.swing.JOptionPane;

import pl.progree.promation.Promation;
import pl.progree.promation.kks.KodKKS;
import pl.progree.promation.kks.ListaKKS;
import pl.progree.promation.kks.StringKKS;
import pl.progree.promation.system.SzafaSystemowa;
import pl.progree.promation.system.SzafaSystemowa.Slot;
import pl.progree.promation.system.SzafaSystemowaFactory;
import pl.progree.promation.templates.SzafaSystemowaTemplate;
import pl.progree.promation.templates.SzafaSystemowaTemplateFactory;

/**
 * @author Progree
 *
 */
public class Tester {
	private Promation pro=new Promation();
	public void start(){
		System.out.println("Witaj w Promation");
		this.showMenu();
	}
	public void showMenu(){
		ArrayList<String> menu=new ArrayList<String>();
		menu.add("Zakoñcz");
		menu.add("Szafy systemowe");
		int wybor=0;
		do{
			wybor=menu.indexOf(JOptionPane.showInputDialog(null,"Wybierz akcje","MENU",
				JOptionPane.QUESTION_MESSAGE,null,
				menu.toArray(),menu.get(0)));
			switch(wybor){
				case 1:this.showMenuSzafSystemowych();;break;
			}
		}while(wybor>0);
	}
	public void showMenuSzafSystemowych(){
		ArrayList<String> menu=new ArrayList<String>();
		menu.add("Powrót");
		menu.add("Szablony szaf systemowych");
		menu.add("Lista szaf systemowych");
		menu.add("Dodaj szafê systemow¹");
		menu.add("Poka¿ szczegó³y szafy");
		int wybor=0;
		do{
			wybor=menu.indexOf(JOptionPane.showInputDialog(null,"Wybierz akcje","MENU SZAF SYSTEMOWYCH",
				JOptionPane.QUESTION_MESSAGE,null,
				menu.toArray(),menu.get(0)));
			switch(wybor){
				case 1:this.showSzablonySzafSystemowych();break;
				case 2:this.showSzafySystemowe();break;
				case 3:this.dodajSzafeSystemowa();break;
				case 4:this.showSzafeSystemowa();break;
				
			}
		}while(wybor>0);
	}
	public void showSzablonySzafSystemowych(){
		
		String formatStr="%3d | %50s \n";
		Collection<SzafaSystemowaTemplate> szablony = pro.getSzablonySzafSystemowych();
		int i=1;
		String str="";
		for (SzafaSystemowaTemplate szafaSystemowaTemplate : szablony) {
			str+=String.format(formatStr, i++,szafaSystemowaTemplate.getOznaczenie());
		}
		JOptionPane.showMessageDialog(null, str);
		System.out.println(str);
	}
	public void showSzafySystemowe(){
			
		String formatStr="%3d | %50s \n";
		Collection<SzafaSystemowa> szafy = pro.getListaSzafSystemowych();
		int i=1;
		String str="";
		for (SzafaSystemowa szafaSystemowa : szafy) {
			str+=String.format(formatStr, i++,szafaSystemowa.getKodKKS().toString());
		}
		JOptionPane.showMessageDialog(null, str);
		System.out.println(str);
	}
	public void dodajSzafeSystemowa(){
		boolean ok=true;
		String kks;
		do{
			kks=JOptionPane.showInputDialog("Podaj KKS szafy");
			ok=pro.getListaSzafSystemowych().find(kks)==null;
			if(!ok) if(JOptionPane.showConfirmDialog(null, "Szafa o danym KKSie ju¿ istnie.\n"
					+ "Czy chcesz spróbowaæ jeszcze raz?")!=JOptionPane.YES_OPTION) return;
		}while(!ok);
		SzafaSystemowaTemplate szablon=this.wybierzSzablonSzafySystemowej();
		if(szablon!=null){
			SzafaSystemowa szafa=SzafaSystemowaFactory.create(new StringKKS(kks), szablon);
			if(pro.addSzafaSystemowa(szafa))JOptionPane.showMessageDialog(null, "Dodano szafê " + szafa.getKKS().toString());
		}
		
		
	}
	public void showSzafeSystemowa(){
		SzafaSystemowa szafa=this.wybierzSzafeSystemowa();
		KodKKS kks;
		if(szafa!=null){
			String str="Szafa: "+szafa.getKKS().toString()+"\n";
			ListaKKS<Slot> sloty=szafa.getListaSlotow();
			for (Slot slot : sloty) {
				str+=slot.getKKS().toString()+" ";
				if(slot.getZaalokowanyModul()==null) str+="PUSTY\n";
				else str+=slot.getZaalokowanyModul().getNazwa()+"\n";
			}
			JOptionPane.showMessageDialog(null, str);
		}
	}
	public SzafaSystemowaTemplate wybierzSzablonSzafySystemowej(){
		Object[] szablony=pro.getSzablonySzafSystemowych().toArray();
		Object wybor;
		wybor=JOptionPane.showInputDialog(null,"Wybierz szablon szafy sytemowej","WYBÓR SZABLONU",
			JOptionPane.QUESTION_MESSAGE,null,
			szablony,szablony[0]);
		return (SzafaSystemowaTemplate) wybor;
	}
	public SzafaSystemowa wybierzSzafeSystemowa(){
		Object[] szafy=pro.getListaSzafSystemowych().toArray();
		Object wybor;
		wybor=JOptionPane.showInputDialog(null,"Wybierz szafê systemow¹","WYBÓR SZAFY SYSTEMOWEJ",
			JOptionPane.QUESTION_MESSAGE,null,
			szafy,szafy[0]);
		return (SzafaSystemowa) wybor;
	}
	 
}
