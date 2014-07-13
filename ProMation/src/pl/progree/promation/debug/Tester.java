/**
 * 
 */
package pl.progree.promation.debug;

import java.util.ArrayList;
import java.util.Collection;

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
		SzafaSystemowa cda10=pro.getListaSzafSystemowych().find("04CDA10");
		SzafaSystemowa cda11=pro.getListaSzafSystemowych().find("04CDA11");
		
		Modul modul=new Modul("CBI20", 32);
		Slot slot=cda10.getListaSlotow().find("A07");
		pro.addModul(modul);
		pro.alokujModul(modul, slot);
		
		modul=new Modul("CBO10", 16);
		slot=cda10.getListaSlotow().find("A08");
		pro.addModul(modul);
		pro.alokujModul(modul, slot);
		
		modul=new Modul("CAI20", 32);
		slot=cda10.getListaSlotow().find("C08");
		pro.addModul(modul);
		pro.alokujModul(modul, slot);
		
		modul=new Modul("CBC11", 20);
		slot=cda10.getListaSlotow().find("E04");
		pro.addModul(modul);
		pro.alokujModul(modul, slot);
		
		modul=new Modul("CBC11", 20);
		slot=cda10.getListaSlotow().find("E05");
		pro.addModul(modul);
		pro.alokujModul(modul, slot);
		
		modul=new Modul("CBC11", 20);
		slot=cda10.getListaSlotow().find("E08");
		pro.addModul(modul);
		pro.alokujModul(modul, slot);
		
		modul=new Modul("CAC10", 4);
		slot=cda10.getListaSlotow().find("G05");
		pro.addModul(modul);
		pro.alokujModul(modul, slot);
		
		modul=new Modul("CAC10", 4);
		slot=cda10.getListaSlotow().find("G09");
		pro.addModul(modul);
		pro.alokujModul(modul, slot);
		
		modul=new Modul("CBC11", 20);
		slot=cda11.getListaSlotow().find("E10");
		pro.addModul(modul);
		pro.alokujModul(modul, slot);
		
		modul=new Modul("CBC11", 20);
		slot=cda11.getListaSlotow().find("E11");
		pro.addModul(modul);
		pro.alokujModul(modul, slot);
		
		modul=new Modul("CBC11", 20);
		slot=cda11.getListaSlotow().find("E12");
		pro.addModul(modul);
		pro.alokujModul(modul, slot);
		
	}
	public void showMenu(){
		ArrayList<String> menu=new ArrayList<String>();
		menu.add("Zako�cz");
		menu.add("Szafy systemowe");
		menu.add("Modu�y");
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
		menu.add("Powr�t");
		menu.add("Szablony szaf systemowych");
		menu.add("Lista szaf systemowych");
		menu.add("Dodaj szaf� systemow�");
		menu.add("Poka� szczeg�y szafy");
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
		menu.add("Powr�t");
		menu.add("Lista modu��w");
		menu.add("Dodaj modu�");
		menu.add("Poka� szczeg�y modu�u");
		menu.add("Alokuj modu�");
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
			if(!ok) if(JOptionPane.showConfirmDialog(null, "Szafa o danym KKSie ju� istnie.\n"
					+ "Czy chcesz spr�bowa� jeszcze raz?")!=JOptionPane.YES_OPTION) return;
		}while(!ok);
		SzafaSystemowaTemplate szablon=this.wybierzSzablonSzafySystemowej();
		if(szablon!=null){
			SzafaSystemowa szafa=SzafaSystemowaFactory.create(new StringKKS(kks), szablon);
			if(pro.addSzafaSystemowa(szafa))JOptionPane.showMessageDialog(null, "Dodano szaf� " + szafa.getKKS().toString());
		}
		
		
	}
	public void dodajModul(){
		boolean ok=true;
		String typ;
		do{
			typ=JOptionPane.showInputDialog("Podaj typ modu�u");
			typ=typ.trim();
			ok=!typ.isEmpty();
			if(!ok) if(JOptionPane.showConfirmDialog(null, "Typ nie mo�e by� pusty.\n"
					+ "Czy chcesz spr�bowa� jeszcze raz?")!=JOptionPane.YES_OPTION) return;
		}while(!ok);
		String liczbaKanalowStr;
		int iloscKanalow;
		do{
			liczbaKanalowStr=JOptionPane.showInputDialog("Podaj ilo�� kana��w");
			iloscKanalow=Integer.valueOf(liczbaKanalowStr);
			ok=iloscKanalow>0;
			if(!ok) if(JOptionPane.showConfirmDialog(null, "Ilo�� kana��w musi by� wi�ksza od 0.\n"
					+ "Czy chcesz spr�bowa� jeszcze raz?")!=JOptionPane.YES_OPTION) return;
		}while(!ok);
		Modul modul=new Modul(typ, iloscKanalow);
		if(pro.addModul(modul))JOptionPane.showMessageDialog(null, "Dodano modu� " + modul.getTyp());
		
		
		
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
		wybor=JOptionPane.showInputDialog(null,"Wybierz szablon szafy sytemowej","WYB�R SZABLONU",
			JOptionPane.QUESTION_MESSAGE,null,
			szablony,szablony[0]);
		return (SzafaSystemowaTemplate) wybor;
	}
	public SzafaSystemowa wybierzSzafeSystemowa(){
		Object[] szafy=pro.getListaSzafSystemowych().toArray();
		Object wybor;
		wybor=JOptionPane.showInputDialog(null,"Wybierz szaf� systemow�","WYB�R SZAFY SYSTEMOWEJ",
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
		wybor=JOptionPane.showInputDialog(null,"Wybierz wolny slot","WYB�R SLOTU",
			JOptionPane.QUESTION_MESSAGE,null,
			sloty,sloty[0]);
		return (Slot) wybor;
	}
	public Modul wybierzModul(){
		Object[] moduly=pro.getListaModulow().toArray();
		Object wybor;
		wybor=JOptionPane.showInputDialog(null,"Wybierz modu�","WYB�R MODU�U",
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
		wybor=JOptionPane.showInputDialog(null,"Wybierz modu�","WYB�R MODU�U",
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
					if(pro.alokujModul(modul, slot))JOptionPane.showMessageDialog(null, "Zaalokowano modu� " + modul.getMiejsceAlokacji().toString());
				}
			}
		}
	}
	 
}
