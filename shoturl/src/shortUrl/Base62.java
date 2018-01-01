package shortUrl;

public class Base62 {
    /**
     * Base62 Character Table
     */
    static final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    // toCharArray() : 문자열의 각 글자를 배열에 넣음. char[0]="A" char[1]="B"
    public static String origin_url = null;
    /**
     * Base62 Encoding
     *
     * @return the base 62 string of an integer
     */
    //encode Base62
    //input value = decimal -> 62
    public static String encode(int value) {
        final StringBuilder sb = new StringBuilder();	//StringBuilder() : 문자열 수정을 용이하게 한다.
        do {
            int i = value % 62;
            sb.append(BASE62[i]);
            value /= 62;
        } while (value > 0);
        return sb.toString();
    }

    public static String encodeToLong(long value) {
        final StringBuilder sb = new StringBuilder();
        do {
            int i = (int)(value % 62);
            sb.append(BASE62[i]);
            value /= 62;
        } while (value > 0);
        return sb.toString();
    }

    
    //calculate sum of each ascii (decimal)
    public static int sum_ascii(String origin_url){
    	int ascii_sum =0;
    	char[] url = origin_url.toCharArray();//cut in array
    	int[] int_url = new int[url.length];

    	for( int i=0; i<url.length; i++){
    		int_url[i] = (int) url[i];//get ascii of each char 
    		ascii_sum += url[i];
    	}
    	return ascii_sum;
    }
}