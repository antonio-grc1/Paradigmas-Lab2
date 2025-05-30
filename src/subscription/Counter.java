package subscription;

public final class Counter{
	private int value;
	public Counter(){
		value = 0;
	}
	
	public void increment(){
		value = value + 1;
	}

	public int getValue(){
		return value;
	}

	public void printValue(){
		System.out.println(value);
	}

	public void main(String[] args) {
		Counter c1 = new Counter();
		Counter c2 = new Counter();
		
		c1.increment();
		c2.printValue();
	}	
}
