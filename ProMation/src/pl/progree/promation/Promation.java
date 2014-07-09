package pl.progree.promation;

import pl.progree.promation.kks.StringKKS;

public class Promation {

	public static void main(String[] args) {
		System.out.println("hello world!");
		StringKKS kks1=new StringKKS("04HLA10AA201");
		System.out.println("kks1:"+kks1);
		StringKKS kks2=new StringKKS("04HLA10AA201","CG01");
		System.out.println("kks2:"+kks2);
		System.out.println("kks1==kks2:"+ kks1.equals(kks2));
		StringKKS kks3=new StringKKS("04HLA10AA201","CG02");
		System.out.println("kks3:"+kks3);
		System.out.println("kks1==kks3:"+ kks1.equals(kks3));
		StringKKS kks4=new StringKKS(null,"CG02");
		System.out.println("kks4:"+kks4);
		StringKKS kks5=new StringKKS("04HLA10AA201");
		System.out.println("kks1:"+kks5);
		System.out.println("kks1==kks5:"+ kks1.equals(kks5));
		StringKKS kks6=new StringKKS("04HLA10AA201","CG01");
		System.out.println("kks6:"+kks6);
		System.out.println("kks2==kks6:"+ kks2.equals(kks6));
	}

}
