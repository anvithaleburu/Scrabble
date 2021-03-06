import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Scrabble {
    String DICTLOCATION="src/sowpods.txt";
	HashMap<String, Integer> wordMap=new HashMap<String, Integer>();
	String letters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	int[] value=new int[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
	HashMap<String, Integer> permutes=new HashMap<String, Integer>();
	public static String res="";
	public static int score=0;
	public static int tempcount=0;
	public static int[] alphas;
	public static int[] alphas_1;
	public static char k;
	public void permute(String str, int l, int r)
    {
        if (l == r)
        {
        	if(wordMap.containsKey(str))
        	{
        		
        		permutes.put(str, wordMap.get(str));
        		if(score<wordMap.get(str) && res.length()<=str.length())
        		{	
        			alphas_1=new int[26];
        			score=wordMap.get(str);
        			res=str;
        			for(int i=0;i<str.length();i++)
    				{
    					alphas_1[k-'A']+=1;
    				}
        			if(alphas_1[k-'A']>=tempcount)
        			{
        				score=score-value[k-'A'];
        			}
        			/*for(int i=0;i<26;i++)
        			{
        				System.out.print(alphas[i]);
        				System.out.print(alphas_1[i]);
        			}
        			System.out.println();*/
        		}
        	}
        	
        }
            //System.out.println(str);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
    }
	
	public String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
	
	 void printCombinations(char[] sequence, int N) 
    {
        char[] data = new char[N];
        for (int r = 0; r < sequence.length; r++)
            combinations(sequence, data, 0, N - 1, 0, r);
    }
 
    void combinations(char[] sequence, char[] data, int start, int end,
            int index, int r) 
    {
 
        if (index == r) 
        {
        	char[] data_1 = new char[r];
            for (int j = 0; j < r; j++)
            	data_1[j]=data[j];
                //System.out.print(data[j] + " ");
        	String s = String.copyValueOf(data_1);
        	permute(s,0,r-1);
        	
            //System.out.println(s);
        }
 
        for (int i = start; i <= end && ((end - i + 1) >= (r - index)); i++) 
        {
            data[index] = sequence[i];
            combinations(sequence, data, i + 1, end, index + 1, r);
        }
    }
	public void populateMap () throws IOException {
		 BufferedReader br = new BufferedReader(new FileReader(DICTLOCATION));
		 String sCurrentLine;
		 while ((sCurrentLine = br.readLine()) != null) {
			 wordMap.put(sCurrentLine, calculateWordScore(sCurrentLine) );
			 //System.out.println(wordMap.get(sCurrentLine));
		 }
		 System.out.println("Done");
	}
	public int calculateWordScore(String s)
	{
		int len=s.length();
		int wordValue=0;
		for(int i=0;i<len;i++){
			wordValue+=value[letters.indexOf(s.charAt(i))];
		}
		return wordValue;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String characters=sc.nextLine();
		Scrabble scrabble=new Scrabble();
		try {
			scrabble.populateMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ch;
		
		for(char c='A'; c<='Z';c++)
		{
		alphas= new int[26];	
			k=c;
			ch=characters+c;
				for(int i=0;i<characters.length();i++)
				{
					alphas[characters.charAt(i)-'A']++;
				}
		  tempcount=alphas[c-'A']+1;	
			scrabble.printCombinations(ch.toCharArray(), ch.length() );
			scrabble.permute(ch,0,ch.length()-1);
		}
		
		System.out.println(res);
    	System.out.println(score);
	}

}
