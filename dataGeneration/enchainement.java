import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


public class enchainement {

	public static void main(String[] args) throws Exception{
		
	/***
	*
	*    Calcul de la hashmap int -> mot
	*
	***/
	
	
	Scanner scan_index = new Scanner(new File("data/" + args[0]+"_index.dat"));
	HashMap<String,Integer> index = new HashMap<String,Integer>();
   
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
			index.put(mot,numero_mot);
			total++;
		}
	}
	
		/***
		*
		*    Calcul de la hashmap mot, mot -> occurences
		*
		***/
		
		
		Scanner sc = new Scanner(new File("data/" + args[0]+".norm"));
		PrintWriter out =  new PrintWriter(new BufferedWriter (new FileWriter("data/"+args[0]+"_enchainement.dat")));

		String buffer1 = "";
		String buffer2 = "";
		HashMap<String,HashMap<String,Integer>> table = new HashMap<String,HashMap<String,Integer>>();

		buffer2 = sc.next();

		while(sc.hasNext()){
			buffer1 = buffer2;
			buffer2 = sc.next();

			if(table.containsKey(buffer1))
			{
				HashMap<String,Integer> table2 = table.get(buffer1);
				if(table2.containsKey(buffer2))
				{
					table2.put(buffer2, table2.get(buffer2)+1);
				}
				else
				{
					table2.put(buffer2, 1);
				}
			}
			else
			{
				HashMap<String,Integer> temp = new HashMap<String,Integer>();
				temp.put(buffer2, 1);
				table.put(buffer1, temp);
			}

		}

		sc.close();

		/***
		*
		*    Sortie .dat
		*
		***/


		out.write("param : enchainement :=\n");
		out.write("# numero_mot1 numero_mot2 valeur\n");
		
		out.write(0 + " " + 0 + " "+ -1 + "\n");
		for(int i=1;i<total;i++)
		{
			out.write(0 + " " + i + " "+ 1 + "\n");
		}
		
		for(String m1 : table.keySet())
		{
			HashMap<String,Integer> table2 = table.get(m1);
			for(String m2 : table2.keySet())
			{
				out.write(index.get(m1) + " " + index.get(m2) + " "+ (table2.get(m2)) + "\n");
			}
		}
		
		out.write(";");
		
		out.close();
	}

}
