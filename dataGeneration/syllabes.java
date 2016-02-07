import java.io.*;
import java.util.Scanner;

public class syllabes {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(new File("data/" + args[0]+".norm"));
		String buffer;
		PrintWriter out =  new PrintWriter(new BufferedWriter (new FileWriter("data/"+args[0]+"_syllabes.dat")));

		int i=0;
		out.write("param : word syl :=\n");
		while(sc.hasNext()){
			buffer = sc.next();
			out.write(i + " \"" + buffer + "\" " + compteSyllabes(buffer) + "\n");
			i++;
		}

		out.write(";");
		sc.close();
		out.close();


  }

	public static int compteSyllabes(String mot){
		String consonnes = "BCDFGHJKLMNPQRSTVWXZ";
		String voyelles = "AEIOUY";

		String type = "";
		for(int i=0; i<mot.length();i++)
		{
			if(voyelles.contains(""+mot.charAt(i)))
			{
				type+='V';
			}
			else if(consonnes.contains(""+mot.charAt(i)))
			{
				type+='C';
			}
			else{
				System.out.println("ERREUR : Le mot ne doit contenir que des lettres majuscules.");
				return 0;
			}
		}

		int n = 0;
		char c1;
		char c2 = 'C';
		for(int i=0; i<mot.length();i++)
		{
			c1 = c2;
			c2 = type.charAt(i);
			if((c1=='C')&&(c2=='V'))
			{
				n+=1;
			}
		}

		return n;
	}


}
