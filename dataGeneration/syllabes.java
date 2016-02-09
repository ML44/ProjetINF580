import java.io.*;
import java.util.Scanner;
import java.util.HashMap;


public class syllabes {

	public static void main(String[] args) throws Exception{
		
		
		/***
		*
		*    Calcul de la hashmap mot -> int
		*
		***/
		
		
	Scanner scan_index = new Scanner(new File("data/" + args[0]+"_index.dat"));
	HashMap<Integer,String> index = new HashMap<Integer,String>();
    
	String mot;
	int numero_mot;
	
	scan_index.nextLine();
	scan_index.nextLine();
	
	String test;
	int total=0;
	
	while(scan_index.hasNext()){
		test = scan_index.next();
		if(!test.equals(";")){
			numero_mot = Integer.parseInt(test);
			mot = scan_index.next();
			index.put(numero_mot,mot);
			total++;
		}
	}
		
	scan_index.close();
		
	/***
	*
	*    Sortie
	*
	***/
	
		
		
		PrintWriter out =  new PrintWriter(new BufferedWriter (new FileWriter("data/"+args[0]+"_syllabes.dat")));

		out.write("param : syllabes : mot nb_syllabes :=\n");
		out.write("# numero_mot nb_syllabes\n");
			
		for(int i=0;i<total;i++){
			out.write(i + " " + compteSyllabes(index.get(i)) + "\n");
		}

		out.write(";");
		out.close();


  }

	public static int compteSyllabes(String mot){
		String consonnes = "BCDFGHJKLMNPQRSTVWXZ";
		String voyelles = "AEIOUY";

		if(mot.equals("mot_vide")){return 0;}

		String type = "";
		for(int i=0; i<mot.length();i++)
		{
			if(voyelles.contains(""+mot.charAt(i)))
			{
				type+='V';
			}
			else if(consonnes.contains(""+mot.charAt(i)))
			{
				type+='C';
			}
			else{
				System.out.println(mot);
				System.out.println("ERREUR : Le mot ne doit contenir que des lettres majuscules.");
				return 0;
			}
		}

		int n = 0;
		char c1;
		char c2 = 'C';
		for(int i=0; i<mot.length();i++)
		{
			c1 = c2;
			c2 = type.charAt(i);
			if((c1=='C')&&(c2=='V'))
			{
				n+=1;
			}
		}

		return n;
	}


}
