import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class rimes {

	public static void main(String[] args) throws Exception {
		HashMap<String, HashSet<Integer>> rimes = new HashMap<String, HashSet<Integer>>();

		Scanner sc = new Scanner(new File("data/index.dat"));
		String ending;
		String buffer;
		sc.nextLine();
		sc.nextLine();

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"data/rimes.dat")));

		Integer numeroMot;
		String test;
		out.write("param : rimes :=\n");
		while (sc.hasNext()) {
			test = sc.next();
			if (!test.equals(";")) {
				numeroMot = Integer.parseInt(test);
				buffer = sc.next();
				ending = buffer.substring(Math.max(0, buffer.length() - 4),
						buffer.length() - 1);
				if (!rimes.containsKey(ending)) {
					rimes.put(ending, new HashSet<Integer>());
				}
				rimes.get(ending).add(numeroMot);
			}
		}

		int num = 0;
		for (String k : rimes.keySet()) {
			for (Integer p : rimes.get(k)) {
				out.println(p + " " + num);
			}
			num++;
		}

		out.write(";");
		sc.close();
		out.close();
		
		PrintWriter lw = new PrintWriter(new BufferedWriter(new FileWriter("data/lastWord.dat")));
		lw.write("param : lastWordArray :=\n");

		int n = rimes.size();
		for(int k = 0;k<7;k++)
		{
			
			int rand,rand1=0,rand2;
			String ma_rime = "";
			
			while((ma_rime.length()<3))
			{
				rand = (int)(Math.random() * n); 
				int i = 0;
				for(String s : rimes.keySet())
				{
				    if (i == rand)
					{
						ma_rime = s;
						break;
					}
				    i = i + 1;
				}
					
				if(rimes.get(ma_rime).size()<2)
				{
					ma_rime = "";
				}
			}	
			
			
			HashSet<Integer> classe = rimes.get(ma_rime);
			int n2 = classe.size();
			int m1=-1,m2=-1;
			
		//	System.out.println(ma_rime + rimes.get(ma_rime).size());	
			
			
			while(m1==-1)
			{
				rand1 = (int)(Math.random() * n2); 
				int i=0;
				for(int l : classe)
				{
				    if (i == rand1)
					{
						m1 = l;
						break;
					}
			    	i = i + 1;				
				}
			}	
			
			while(m2==-1)
			{
				rand2 = (int)(Math.random() * n2);
				while(rand1==rand2)
					{rand2 = (int)(Math.random() * n2);} 
				int i=0;
				for(int l : classe)
				{
				    if (i == rand2)
					{
						m2 = l;
						break;
					}
				    i = i + 1;
				}				
			}

			
							
			
			lw.write((2*k+1)+" "+m1+"\n");
			lw.write((2*k+2)+" "+m2+"\n");
			
		}
		
		lw.write(";");
		lw.close();

	}

}
