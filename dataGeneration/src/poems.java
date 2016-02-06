import java.io.File;
import java.util.HashMap;
import java.util.Scanner;



public class poems {
	
	public static void main(String[] args) throws Exception{
		int k = 2;
	    Scanner sc = new Scanner(new File("data/" + args[0]+".txt"));
	    String[] buffer_old = new String[k];
	    HashMap<String[],Integer> table = new HashMap<String[],Integer>();
	    int j;
	    
	    for(int i=0;i<k;i++)
	    {
	    	if(sc.hasNext())
	    	{
		    	buffer_old[i] = sc.next();	    		
	    	}
	    	else {System.out.println("ERREUR : texte trop court."); return;}
	    }
	    
	    do{
	    	String[] buffer = new String[k];
	    	for(int i=0;i<k-1;i++)
	    	{
	    		buffer[i] = buffer_old[i+1];
	    	}
    		buffer[k-1] = sc.next();

	    	if(table.containsKey(buffer)) {j = table.get(buffer) + 1;}
	    	else {j=1;}
	    	table.put(buffer, j);
	    	buffer_old = buffer;
	    }while(sc.hasNext());
	    
	    sc.close();
	    
	    for(String[] val : table.keySet())
	    {
	    	{
	    		System.out.println(table.get(val) + "\t" + val[0] + "\t" + val[1]);
	    	}
	    }
	}

}
