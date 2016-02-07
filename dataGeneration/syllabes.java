public class syllabes {

	public static void main(String[] args){
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String voyelles = "AEIOUY";
    String s = args[0];

		if(args.length!=1)
		{
			System.out.println("ERREUR : Le programme attend un unique argument.");
			return;
		}

		for(int i=0; i<s.length();i++)
		{
			if(!(alphabet.contains(""+s.charAt(i))))
			{
				System.out.println("ERREUR : Le mot ne doit contenir que des lettres majuscules.");
				return;
			}
		}

		String type = "";
		for(int i=0; i<s.length();i++)
		{
			if(voyelles.contains(""+s.charAt(i)))
			{
				type+='V';
			}
			else
			{
				type+='C';
			}
		}

		int n = 0;
		char c1;
		char c2 = 'C';
		for(int i=0; i<s.length();i++)
		{
			c1 = c2;
			c2 = type.charAt(i);
			if((c1=='C')&&(c2=='V'))
			{
				n+=1;
			}
		}

		System.out.println(n);
  }

}
