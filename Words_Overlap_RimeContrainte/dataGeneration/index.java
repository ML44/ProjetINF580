import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


public class index {

	public static void main(String[] args) throws Exception{
	    Scanner sc = new Scanner(new File("data/text.norm"));
		PrintWriter out =  new PrintWriter(new BufferedWriter (new FileWriter("data/index.dat")));

	    String buffer = "";
	    HashMap<String,Integer> table = new HashMap<String,Integer>();
	    HashMap<Integer,String> table_inv = new HashMap<Integer,String>();

		
		table.put("mot_vide",0);
		table_inv.put(0,"mot_vide");
		int i = 1;
	    while(sc.hasNext()){
	    	buffer = sc.next();
			
	    	if(!table.containsKey(buffer))
	    	{
	    		table.put(buffer, i);
				table_inv.put(i, buffer);
				i++;
			}
	    }

	    sc.close();

		out.write("param : index :=\n");
		out.write("# mot numero_mot\n");

	    for(int k=0;k<i;k++)
	    {
		 	out.write(k + " " + table_inv.get(k) + "\n");
	    }
		out.write(";\n\n");
		out.close();
		
		PrintWriter out_n =  new PrintWriter(new BufferedWriter (new FileWriter("data/n.dat")));
		
		out_n.write("param n :=" + (i-1) + " ;");
		
		out_n.close();
		
		
	}

}
