
public class SynchronousAccumulator extends Accumulator {
	@Override
	public synchronized void add(int amount) {
		super.add(amount);
	}
}
