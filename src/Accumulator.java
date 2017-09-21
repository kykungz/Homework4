public class Accumulator {
	public long value;

	public Accumulator() {
	}

	public void add(int amount) {
		value = value + amount;
	}

	public long get() {
		return value;
	}

}
