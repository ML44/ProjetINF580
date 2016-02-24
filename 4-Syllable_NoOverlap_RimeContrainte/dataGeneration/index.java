import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;

public class index {

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("data/text.norm"));		
		PrintWriter out =  new PrintWriter(new BufferedWriter (new FileWriter("data/index.dat")));

	    String buffer = "";
	    HashMap<String,Integer> table = new HashMap<String,Integer>();
	    HashMap<Integer,String> table_inv = new HashMap<Integer,String>();

		
	//	table.put("mot_vide",0);
	//	table_inv.put(0,"mot_vide");
		int i = 0;
	    char c_old='C';
		char c_new;
		int next=in.read();
		while(next!=-1){
			c_new = (char) next;
			//System.out.println(""+c_new);
			
			if(is_voyelle(c_old) && (!is_voyelle(c_new)))
			{
		    	if(!table.containsKey(buffer))
		    	{
		    		table.put(buffer, i);
					table_inv.put(i, buffer);
					i++;
				}
				buffer = ""+c_new;
			}
			else
			{
				buffer += c_new;
			}
			
			c_old = c_new;
			next=in.read();
	    }

	    in.close();

		out.write("param : index :=\n");
		out.write("# mot numero_mot\n");

	    for(int k=0;k<i;k++)
	    {
		 	out.write(k + " " + table_inv.get(k) + "\n");
	    }
		out.write(";\n\n");
		out.close();
		
		PrintWriter out_n =  new PrintWriter(new BufferedWriter (new FileWriter("data/n.dat")));
		
		out_n.write("param n :=" + i + " ;");
		
		out_n.close();
		
		
	}
	
	public static boolean is_voyelle(char c){
		String voyelles = "AEIOUY";
		return voyelles.contains(""+c);
	}

}
