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
import pl.progree.promation.kks.ListaKKS.Selector;
import pl.progree.promation.kks.StringKKS;
import pl.progree.promation.system.Modul;
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
		this.loadModuly();
		this.showMenu();
	}
	public void loadModuly(){
		pro.addModul(new Modul("CBI20", 32));
		pro.addModul(new Modul("CBO10", 16));
		pro.addModul(new Modul("CAI20", 32));
		pro.addModul(new Modul("CBC11", 20));
		pro.addModul(new Modul("CBC11", 20));
		pro.addModul(new Modul("CBC11", 20));
		pro.addModul(new Modul("CBC11", 20));
		pro.addModul(new Modul("CBC11", 20));
		pro.addModul(new Modul("CBC11", 20));
		pro.addModul(new Modul("CAC10", 4));
		pro.addModul(new Modul("CAC10", 4));
	}
	public void showMenu(){
		ArrayList<String> menu=new ArrayList<String>();
		menu.add("Zakoñcz");
		menu.add("Szafy systemowe");
		menu.add("Modu³y");
		int wybor=0;
		do{
			wybor=menu.indexOf(JOptionPane.showInputDialog(null,"Wybierz akcje","MENU",
				JOptionPane.QUESTION_MESSAGE,null,
				menu.toArray(),menu.get(0)));
			switch(wybor){
				case 1:this.showMenuSzafSystemowych();break;
				case 2:this.showMenuModulow();break;
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
	public void showMenuModulow(){
		ArrayList<String> menu=new ArrayList<String>();
		menu.add("Powrót");
		menu.add("Lista modu³ów");
		menu.add("Dodaj modu³");
		menu.add("Poka¿ szczegó³y modu³u");
		menu.add("Alokuj modu³");
		int wybor=0;
		do{
			wybor=menu.indexOf(JOptionPane.showInputDialog(null,"Wybierz akcje","MENU SZAF SYSTEMOWYCH",
				JOptionPane.QUESTION_MESSAGE,null,
				menu.toArray(),menu.get(0)));
			switch(wybor){
				case 1:this.showModuly();break;
				case 2:this.dodajModul();break;
				case 3:this.showModul();break;
				case 4:this.alokujModul();break;
				
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
	public void showModuly(){
		
		String formatStr="%3d | %50s \n";
		Collection<Modul> moduly = pro.getListaModulow();
		int i=1;
		String str="";
		for (Modul modul : moduly) {
			str+=String.format(formatStr, i++,modul.getTyp());
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
	public void dodajModul(){
		boolean ok=true;
		String typ;
		do{
			typ=JOptionPane.showInputDialog("Podaj typ modu³u");
			typ=typ.trim();
			ok=!typ.isEmpty();
			if(!ok) if(JOptionPane.showConfirmDialog(null, "Typ nie mo¿e byæ pusty.\n"
					+ "Czy chcesz spróbowaæ jeszcze raz?")!=JOptionPane.YES_OPTION) return;
		}while(!ok);
		String liczbaKanalowStr;
		int iloscKanalow;
		do{
			liczbaKanalowStr=JOptionPane.showInputDialog("Podaj iloœæ kana³ów");
			iloscKanalow=Integer.valueOf(liczbaKanalowStr);
			ok=iloscKanalow>0;
			if(!ok) if(JOptionPane.showConfirmDialog(null, "Iloœæ kana³ów musi byæ wiêksza od 0.\n"
					+ "Czy chcesz spróbowaæ jeszcze raz?")!=JOptionPane.YES_OPTION) return;
		}while(!ok);
		Modul modul=new Modul(typ, iloscKanalow);
		if(pro.addModul(modul))JOptionPane.showMessageDialog(null, "Dodano modu³ " + modul.getTyp());
		
		
		
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
				else str+=slot.getZaalokowanyModul().getTyp()+"\n";
			}
			JOptionPane.showMessageDialog(null, str);
		}
	}
	public void showModul(){
		Modul modul=this.wybierzModul();
		if(modul!=null){
			String str=String.format("Typ: %s\nMiejsce alokacji:%s", modul.getTyp(),modul.getKKS().toString());
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
	public Slot wybierzWolnySlot(SzafaSystemowa szafa){
		Object[] sloty=szafa.getListaSlotow().subList(new Selector<SzafaSystemowa.Slot>() {
			
			@Override
			public boolean Select(Slot obiektDoPorownania) {
				return obiektDoPorownania.getZaalokowanyModul()==null;
			}
		}).toArray();
		Object wybor;
		wybor=JOptionPane.showInputDialog(null,"Wybierz wolny slot","WYBÓR SLOTU",
			JOptionPane.QUESTION_MESSAGE,null,
			sloty,sloty[0]);
		return (Slot) wybor;
	}
	public Modul wybierzModul(){
		Object[] moduly=pro.getListaModulow().toArray();
		Object wybor;
		wybor=JOptionPane.showInputDialog(null,"Wybierz modu³","WYBÓR MODU£U",
			JOptionPane.QUESTION_MESSAGE,null,
			moduly,moduly[0]);
		return (Modul) wybor;
	}
	public Modul wybierzModulNiezaalokowany(){
		Object[] moduly=pro.getListaModulow().subList(new Selector<Modul>() {
			
			@Override
			public boolean Select(Modul obiektDoPorownania) {
				return obiektDoPorownania.getMiejsceAlokacji()==null;
			}
		}).toArray();
		Object wybor;
		wybor=JOptionPane.showInputDialog(null,"Wybierz modu³","WYBÓR MODU£U",
			JOptionPane.QUESTION_MESSAGE,null,
			moduly,moduly[0]);
		return (Modul) wybor;
	}
	public void alokujModul(){
		Modul modul=this.wybierzModulNiezaalokowany();
		if(modul!=null){
			SzafaSystemowa szafa=this.wybierzSzafeSystemowa();
			if(szafa!=null){
				Slot slot=this.wybierzWolnySlot(szafa);
				if(slot!=null){
					if(pro.alokujModul(modul, slot))JOptionPane.showMessageDialog(null, "Zaalokowano modu³ " + modul.getMiejsceAlokacji().toString());
				}
			}
		}
	}
	 
}
