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


		out.write("param : enchainement : mot1 mot2 valeur :=\n");
		out.write("# numero_mot1 numero_mot2 valeur\n");
		
		String m1;
		String m2;
		
		for(int i1=0;i1<total;i1++)
		{
			m1 = index.get(i1);
			if(table.containsKey(m1))
			{
				HashMap<String,Integer> table2 = table.get(m1);
				
				for(int i2=0;i2<total;i2++)
				{
					m2 = index.get(i2);
					if(table2.containsKey(m2))
					{
						out.write(i1 + " " + i2 + " "+ (table2.get(m2)) + "\n");
					}
					else
					{
						//out.write(i1 + " " + i2 + " "+ 0 + "\n");
					}	
				}
				
			}
			else
			{
				for(int i2=0;i2<total;i2++)
				{
					m2 = index.get(i2);
					//out.write(i1 + " " + i2 + " "+ 0 + "\n");	
				}				
			}
		}
		out.write(";");
		
		out.close();
	}

}
