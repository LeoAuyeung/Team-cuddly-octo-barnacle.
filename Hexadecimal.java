//Team cuddly-octo-barnacle -- Leo Au-Yeung, Elias Milborn
//APCS1 pd10
//HW44 -- This or That or Fourteen Other Things
//2015-12-08

//skeleton file for class Hexadecimal

public class Hexadecimal {
	
    private int _decNum;
    private String _hexNum;
	private final static String HEXDIGITS = "0123456789ABCDEF"; 
	
    /*=====================================
		default constructor
		pre:  n/a
		post: initializes _decNum to 0, _hexNum to "0"
	=====================================*/
    public Hexadecimal() { 
		_decNum = 0;
		_hexNum = "0";
	}
	
	
    /*=====================================
		overloaded constructor
		pre:  n >= 0
		post: sets _decNum to n, _hexNum to equiv string of bits
	=====================================*/
    public Hexadecimal( int n ) {
		_decNum = n;
		_hexNum = decToHex(_decNum);
	}
	
	
    /*=====================================
		overloaded constructor
		pre:  s is String representing non-negative hexadecimal number
		post: sets _hexNum to input, _decNum to decimal equiv
	=====================================*/
    public Hexadecimal( String s ) {
		_hexNum = s;
		_decNum = hexToDec(s);
	}
	
	
    /*=====================================
		String toString() -- returns String representation of this Object
		pre:  n/a
		post: returns String of 1's and 0's representing value of this Object
	=====================================*/
    public String toString() { 
		return _hexNum;
	}
	
	
    /*=====================================
		String decToHex(int) -- converts base-10 input to hexadecimal
		Dec -> Hex conv:
		1. Div decNum by 16, store remainder
		2. Save quotient as new decNum
		3. Repeat 1 & 2 til decNum == 0
		4. Stored remainders constitute hexNum
		pre:  n >= 0
		post: returns String of bits
		eg  decToBin(0) -> "0"
		decToBin(1) -> "1"
		decToBin(2) -> "10"
		decToBin(3) -> "11"
		decToBin(14) -> "1110"
	=====================================*/
    public static String decToHex( int n ) {
		String ans = "";
		int quotient = n;
		int remainder;
		while ( quotient != 0 ) {
			remainder = quotient % 16;
			quotient = quotient / 16;
			ans = HEXDIGITS.substring(remainder, remainder + 1) + ans;
		}
		return ans;
	}
	
	
	/*=====================================
		String decToBinR(int) -- converts base-10 input to hexadecimal, recursively
		pre:  n >= 0
		post: returns String of bits
		eg  decToBinR(0) -> "0"
		decToBinR(1) -> "1"
		decToBinR(2) -> "10"
		decToBinR(3) -> "11"
		decToBinR(14) -> "1110"
	=====================================*/
	public static String decToHexR( int n ) { 
		String ans = "";
		if (n < 16) {
			ans += HEXDIGITS.substring(n, n + 1);
		}
		else {
			ans = decToHexR(n / 16) + HEXDIGITS.substring((n % 16), (n % 16) + 1);
		}
		return ans;
	}
	
	
	/*=====================================
		String hexToDec(String) -- converts base-10 input to hexadecimal
		pre:  s represents non-negative hexadecimal number
		post: returns decimal equivalent as int
		eg  
		hexToDec("0") -> 0
		hexToDec("1") -> 1
		hexToDec("10") -> 2
		hexToDec("11") -> 3
		hexToDec("1110") -> 14
	=====================================*/
	public static int hexToDec( String s ) {
		int ans = 0;
		for (int x = s.length(); x > 0 ; x += 1) {
			ans += Math.pow(16, Integer.parseInt(s.substring(x - 1, x)));
		}
		return ans;
	}
	
	
	/*=====================================
		String hexToDecR(String) -- converts base-10 input to hexary, recursively
		pre:  s represents non-negative hexary number
		post: returns decimal equivalent as int
		eg  
		hexToDecR("0") -> 0
		hexToDecR("1") -> 1
		hexToDecR("10") -> 2
		hexToDecR("11") -> 3
		hexToDecR("1110") -> 14
	=====================================*/
	public static int hexToDecR( String s ) { 
		int ans = 0;
		int n = Integer.parseInt(s);
		if (n < 16) {
			ans += n;
		}
		else {
			ans = hexToDecR(s.substring(0, s.length() - 1)) + (int) ( Math.pow(16, (n % 16) * s.length() ));
		}
		return ans;
	}
	
	
	/*=============================================
		boolean equals(Object) -- tells whether 2 Objs are equivalent
		pre:  other is an instance of class Hexadecimal
		post: Returns true if this and other are aliases (pointers to same 
		Object), or if this and other represent equal hexary values
	=============================================*/
	public boolean equals( Object other ) { 
		return ( this == other || this._hexNum.equals( ((Hexadecimal)other)._hexNum ) );
	}
	
	
	/*=============================================
		int compareTo(Object) -- tells which of two Hexadecimal objects is greater
		pre:  other is instance of class Hexadecimal
		post: Returns 0 if this Object is equal to the input Object,
		negative integer if this<input, positive integer otherwise
	=============================================*/
	public int compareTo( Object other ) {
		if (this.equals(other)) {
			return 0;
		}
		else if (this._decNum < ((Hexadecimal)other)._decNum) {
			return -1;
			}
		else { 
			return 1;
		}
	}
	
	
	//main method for testing
	public static void main( String[] args ) {
		
			System.out.println();
			System.out.println( "Testing ..." );
			
			Hexadecimal b1 = new Hexadecimal(15);
			Hexadecimal b2 = new Hexadecimal(15);
			Hexadecimal b3 = b1;
			Hexadecimal b4 = new Hexadecimal(27);
			
			System.out.println( b1 );
			System.out.println( b2 );
			System.out.println( b3 );       
			System.out.println( b4 );       
			
			System.out.println( "\n==..." );
			System.out.println( b1 == b2 ); //should be false
			System.out.println( b1 == b3 ); //should be true
			
			System.out.println( "\n.equals()..." );
			System.out.println( b1.equals(b2) ); //should be true
			System.out.println( b1.equals(b3) ); //should be true
			System.out.println( b3.equals(b1) ); //should be true
			System.out.println( b4.equals(b2) ); //should be false
			System.out.println( b1.equals(b4) ); //should be false

			System.out.println( "\n.compareTo..." );
			System.out.println( b1.compareTo(b2) ); //should be 0
			System.out.println( b1.compareTo(b3) ); //should be 0
			System.out.println( b1.compareTo(b4) ); //should be neg
			System.out.println( b4.compareTo(b1) ); //should be pos
			/*=========================================
		=========================================*/
	}//end main()
	
} //end class

