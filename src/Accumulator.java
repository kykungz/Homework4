public class Accumulator {
    public long value;

    public Accumulator() {
    }

    public synchronized void add(int amount) {
	value = value + amount;
    }

    public long get() {
	return value;
    }

}
