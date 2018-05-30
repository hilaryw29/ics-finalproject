package frac;

public class Fraction {
	private int num;
	private int dem;
	
	public boolean equals (Fraction other) {
		if (other != null && other.num == num && other.dem == dem) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return num + "/" + dem;
	}
	
	public Fraction() {	
	}
	
	public Fraction (int n, int d) {
		num = n;
		dem = d;
	}

	public Fraction (Fraction f) {
		num = f.getNumerator();
		dem = f.getDenominator();
	}
	
	public Fraction (double d) {
		double dnum = d;
		double ddem = 1;
		
		while(dnum % 1 != 0) {
			dnum = dnum * 10;
			ddem *= 10;
		}
		num = (int) dnum;
		dem = (int) ddem;
	}
	   
	   
	public Fraction times (Fraction other) {
	   Fraction f = new Fraction(num * other.num, other.dem);
	   return f;
	}
	
	public int getNumerator() {
		return num;
	}
	
	public int getDenominator() {
		return dem;
	}
	   
	public void setDenominator(int n) {
		dem = n;
	}
	
	public void setNumerator(int n) {
		num = n;
	}
	
	public Fraction plus (Fraction other) {
	   Fraction f = new Fraction(num * other.dem + other.num * dem, dem * other.dem);
	   return f;
	}
	   
	public Fraction larger (Fraction other) {
	   if (this.size() >= other.size() ) {
	      return this;
	   } else {
		   return other;
	   }
	}
	   
	private double size () {
	   return Math.abs(1.0 * num / dem);
	}
	   
	public void timesEquals (Fraction p) {
	   num = num * p.num;
	   dem = dem * p.dem;
	}
	   
	public void reduce (){
	   boolean done = false;
	   for (int i = dem; i >= 1 && !done; i--) {
	      if (1.0 * num / i == (int)(1.0 * num / i) && 1.0 * dem / i == (int)(1.0 * dem / i)) {
	         num /= i;
	         dem /= i;
	         done = true;
	      }
	   }
	}
	
	public static Fraction staticProduct (Fraction f1, Fraction f2) {
		Fraction current = new Fraction(f1.num * f2.num, f1.dem * f2.dem);
		return current;
	}
	
	public static Fraction abs (Fraction f) {
		return new Fraction(Math.abs(f.num), Math.abs(f.dem)); 
	}
	
	public static boolean isPositive (Fraction f) {
		if (f.dem > 0 && f.num > 0) {
			return true;
		}
		return false;
	}
}