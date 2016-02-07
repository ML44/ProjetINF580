import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


public class succession {

	public static void main(String[] args) throws Exception{
	    Scanner sc = new Scanner(new File("data/" + args[0]+".norm"));
			PrintWriter out =  new PrintWriter(new BufferedWriter (new FileWriter("data/"+args[0]+"_succession.dat")));

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

		out.write("param : word nextword freq :=\n");
		
		int i=0;
	    for(String s1 : table.keySet())
	    {
	    	HashMap<String,Integer> t2 = table.get(s1);
			
			int j=0;
		    for(String s2 : t2.keySet())
	    	{
		    		j+=t2.get(s2);
		    }
			
		    for(String s2 : t2.keySet())
	    	{
		    		out.write(i + " " + s1 + " " + s2 + " "+ (( (double) t2.get(s2)) / j) + "\n");
						i++;
		    }
	    }
		out.write(";");
		
		out.close();
	}

}
