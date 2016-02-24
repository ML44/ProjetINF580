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
	
	
	Scanner scan_index = new Scanner(new File("data/index.dat"));
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
		
		
			BufferedReader in = new BufferedReader(new FileReader("data/text.norm"));		
		PrintWriter out =  new PrintWriter(new BufferedWriter (new FileWriter("data/enchainement.dat")));

		String buffer1 = "";
		String buffer2 = "";
		HashMap<String,HashMap<String,Integer>> table = new HashMap<String,HashMap<String,Integer>>();


	    char c_old='C';
		char c_new;
		int next=in.read();
		//calcul de buffer1
		while(!is_voyelle(c_old))
		{
			c_new = (char) next;
			buffer1+=c_new;
			c_old = c_new;
			next = in.read();
		}
		//calcul de buffer2
		while(next!=-1){
			c_new = (char) next;
			//System.out.println(""+c_new);
			
			if(is_voyelle(c_old) && (!is_voyelle(c_new)))
			{

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
		    	buffer1 = buffer2;
				buffer2 = ""+c_new;
			}
			else
			{
				buffer2 += c_new;
			}
			
			c_old = c_new;
			next=in.read();
	    }


		in.close();

		/***
		*
		*    Sortie .dat
		*
		***/


		out.write("param : enchainement :=\n");
		out.write("# numero_mot1 numero_mot2 valeur\n");
		
		for(String m1 : table.keySet())
		{
			HashMap<String,Integer> table2 = table.get(m1);
			for(String m2 : table2.keySet())
			{
				if((index.get(m1)!=null)&&(index.get(m2)!=null))
				{
					int im1 = index.get(m1);
					int im2 = index.get(m2);
				
					out.write(im1 + " " + im2 + " "+ (table2.get(m2)) + "\n");
					
				}
				
			}
		}
		
		out.write(";");
		
		out.close();
	}
	
	public static boolean is_voyelle(char c){
		String voyelles = "AEIOUY";
		return voyelles.contains(""+c);
	}

}
