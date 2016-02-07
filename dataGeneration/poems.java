import java.io.File;
import java.util.HashMap;
import java.util.Scanner;


public class poems {

	public static void main(String[] args) throws Exception{
	    Scanner sc = new Scanner(new File("data/" + args[0]+".txt"));
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

	    for(String s1 : table.keySet())
	    {
	    	HashMap<String,Integer> t2 = table.get(s1);
		    for(String s2 : t2.keySet())
	    	{
		    		System.out.println(t2.get(s2) + "\t" + s1 + "\t" + s2);
		    }
	    }
	}

}
