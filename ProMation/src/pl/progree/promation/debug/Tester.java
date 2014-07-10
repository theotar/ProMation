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
import pl.progree.promation.templates.SzafaSystemowaTemplate;

/**
 * @author Progree
 *
 */
public class Tester {
	private Promation pro=new Promation();
	private Scanner k=new Scanner(System.in);
	public void start(){
		System.out.println("Witaj w Promation");
		this.showMenu();
	}
	public void showMenu(){
		ArrayList<String> menu=new ArrayList<String>();
		menu.add("Zakoñcz");
		menu.add("Szablony szaf systemowych");
		int wybor=0;
		do{
			wybor=menu.indexOf(JOptionPane.showInputDialog(null,"Wybierz akcje","MENU",
				JOptionPane.QUESTION_MESSAGE,null,
				menu.toArray(),menu.get(0)));
			switch(wybor){
				case 1:this.showSzablonySzafSystemowych();break;
			}
		}while(wybor!=0);
	}
	public void showMenuSzafSystemowych(){
		ArrayList<String> menu=new ArrayList<String>();
		menu.add("Powrót");
		menu.add("Lista szaf systemowych");
		menu.add("Dodaj szafê systemow¹");
		int wybor=0;
		do{
			wybor=menu.indexOf(JOptionPane.showInputDialog(null,"Wybierz akcje","MENU SZAF SYSTEMOWYCH",
				JOptionPane.QUESTION_MESSAGE,null,
				menu.toArray(),menu.get(0)));
			switch(wybor){
				case 1:this.showSzablonySzafSystemowych();break;
			}
		}while(wybor!=0);
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
}
