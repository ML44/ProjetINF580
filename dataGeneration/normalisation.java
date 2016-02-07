import java.text.Normalizer;
import java.io.*;

public class normalisation {

	public static void main(String[] args) throws Exception{

	String s = args[0];

	// 1e étape : lecture ligne par ligne pour enlever les accents

		BufferedReader in = new BufferedReader(new FileReader("data/"+s+".txt"));
		PrintWriter out =  new PrintWriter(new BufferedWriter (new FileWriter("data/"+s+".tmp")));

		String line;

		while(in.ready())
		{
			line = in.readLine();
		    line = Normalizer.normalize(line, Normalizer.Form.NFD);
		    line = line.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		    out.write(line);
		}

		in.close();
		out.close();

	// 2e étape : lecture caractère par caractère pour mettre en majuscule et enlever la ponctuation sauf les espaces

		int c=0, c_old=' ';
		FileInputStream in2 = new FileInputStream("data/"+s+".tmp");
		FileOutputStream out2 = new FileOutputStream("data/"+s+".norm");

		while(c!=-1){
			c = in2.read();
			if(c>='a'&&c<='z'){out2.write(c+'A'-'a');}
			else if(c>='A'&&c<='Z'){out2.write(c);}
			else if(c!=-1)
			{
				c=' ';
				if(c_old!=' '){out2.write(c);} // Pas 2 espaces de suite !
			}
			c_old=c;
		}

		in2.close();
		out2.close();

	// supprimer le fichier .tmp
		new File ("data/"+s+".tmp").delete();
	}
}
