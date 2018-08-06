
public class permutations {

	public static String res="";
	public int score=0;
	
	public static void permute(String str, int l, int r)
    {
        if (l == r)
        {
        /*	if(wordMap.containsKey(str))
        	{
        		if(score<wordMap.get(str))
        		{
        			score=wordMap.get(str);
        			res=str;
        		}
        	}*/
        	System.out.println(str);
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
	
	public static String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
	
	static void printCombinations(char[] sequence, int N) 
    {
        char[] data = new char[N];
        for (int r = 0; r < sequence.length; r++)
            combinations(sequence, data, 0, N - 1, 0, r);
    }
 
    static void combinations(char[] sequence, char[] data, int start, int end,
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
        	
            System.out.println(s);
        }
 
        for (int i = start; i <= end && ((end - i + 1) >= (r - index)); i++) 
        {
            data[index] = sequence[i];
            combinations(sequence, data, i + 1, end, index + 1, r);
        }
    }
 
    public static void main(String args[]) 
    {
        char[] sequence = { 'a', 'a', 'c' };
        System.out.print("The combinations are: ");
        printCombinations(sequence, sequence.length);
        String s1 = String.copyValueOf(sequence);
    	
        permute(s1,0,sequence.length-1);
        System.out.println(res);
    }

}
