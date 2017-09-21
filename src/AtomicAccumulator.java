import java.util.concurrent.atomic.AtomicLong;

public class AtomicAccumulator extends Accumulator {
	private AtomicLong total;

	public AtomicAccumulator() {
		total = new AtomicLong();
	}

	/** add amount to the total. */
	public void add(int amount) {
		total.getAndAdd(amount);
	}

	/** return the total as an int value. */
	public long get() {
		return total.intValue();
	}
}