import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


public class index {

	public static void main(String[] args) throws Exception{
	    Scanner sc = new Scanner(new File("data/" + args[0]+".norm"));
			PrintWriter out =  new PrintWriter(new BufferedWriter (new FileWriter("data/"+args[0]+"_index.dat")));

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

		out.write("param : index : mot :=\n");
		out.write("# mot numero_mot\n");

	    for(int k=0;k<i;k++)
	    {
		 	out.write(k + " " + table_inv.get(k) + "\n");
	    }
		out.write(";");
		
		out.close();
	}

}
