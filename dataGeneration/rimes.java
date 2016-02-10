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
		LinkedList<String> KeyList = new LinkedList<String>();
		HashMap<String, HashSet<Integer>> rimes = new HashMap<String, HashSet<Integer>>();

		Scanner sc = new Scanner(new File("data/" + args[0] + "_index.dat"));
		String ending;
		String buffer;
		sc.nextLine();
		sc.nextLine();

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"data/" + args[0] + "_rimes.dat")));

		Integer numeroMot;
		String test;
		out.write("param : word rime :=\n");
		while (sc.hasNext()) {
			test = sc.next();
			if (!test.equals(";")) {
				numeroMot = Integer.parseInt(test);
				buffer = sc.next();
				ending = buffer.substring(Math.max(0, buffer.length() - 4),
						buffer.length() - 1);
				if (!rimes.containsKey(ending)) {
					KeyList.push(ending);
					rimes.put(ending, new HashSet<Integer>());
				}
				rimes.get(ending).add(numeroMot);
			}
		}

		int i = 0;
		for (String k : KeyList) {
			for (Integer p : rimes.get(k)) {
				out.println(p + " " + i);
			}
			i++;
		}

		out.write(";");
		sc.close();
		out.close();

	}

}
