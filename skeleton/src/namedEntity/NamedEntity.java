package namedEntity;

import subscription.Counter;

/*Esta clase modela la nocion de entidad nombrada*/

public class NamedEntity {
	String name;
	String category;
	int frequency;
	Type type;
	static Counter counter = new Counter();

	public NamedEntity(String name, String category, int frequency, Type type) {
		super();
		this.name = name;
		this.category = category;
		this.frequency = frequency;
		this.type = type;
	}

	public Counter getCounter(){
		return counter;
	}

	public void incrementCounter(){
		counter.increment();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public void incFrequency() {
		this.frequency++;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type newType) {
		this.type = newType;
	}

	@Override
	public String toString() {
		return "ObjectNamedEntity [name=" + name + ", frequency=" + frequency + "]";
	}

	public void printHeader() {
		System.out.println();
		System.out.println(String.format("%-15s | %-8s | %-10s | %-10s", "Name", "Frequency", "Category", "Type"));
		System.out.println("----------------|-----------|------------|--------------");
	}

	public void prettyPrint() {
		System.out.println(String.format("%-15s | %-9d | %-10s | %-10s",
				this.getName(),
				this.getFrequency(),
				this.getCategory(),
				this.getType().getClass().getSimpleName()
				));
	}

}
