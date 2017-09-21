public class ThreadSum {
	// upper limit of numbers to add/subtract to Accumulator
	static int LIMIT = 100000;

	public static void main(String[] args) {
		run(new AccumulatorWithLock());
		run(new SynchronousAccumulator());
		run(new AtomicAccumulator());
	}

	public static void run(Accumulator accum) {
		// two tasks that send "add" messages to same accumulator
		AddTask addtask = new AddTask(accum, LIMIT);
		SubtractTask subtask = new SubtractTask(accum, LIMIT); // threads to run
		// the tasks
		Thread thread1 = new Thread(addtask);
		Thread thread2 = new Thread(subtask); // start the tasks
		System.out.println("Starting threads: " + accum.getClass().getName());
		long startTime = System.nanoTime();
		thread1.start();
		thread2.start();
		// wait for threads to finish
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			System.out.println("Threads interrupted");
		}
		double elapsed = 1.0E-9 * (System.nanoTime() - startTime);
		// the sum should be 0. Is it?
		System.out.printf("Accumulator total is %d\n", accum.get());
		System.out.printf("Elapsed %.6f sec\n", elapsed);
	}

	/** AddTask adds number 1 .. limit to the accumulator. */
	public static class AddTask implements Runnable {
		private Accumulator acc;
		private int limit;

		public AddTask(Accumulator acc, int limit) {
			this.acc = acc;
			this.limit = limit;
		}

		public void run() {
			for (int k = 1; k <= limit; k++) {
				acc.add(k);
			}
		}
	}

	/** SubtractTask subtracts 1 .. limit from the accumulator total. */
	public static class SubtractTask implements Runnable {
		private Accumulator acc;
		private int limit;

		public SubtractTask(Accumulator acc, int limit) {
			this.acc = acc;
			this.limit = limit;
		}

		public void run() {
			for (int k = 1; k <= limit; k++) {
				acc.add(-k);
			}
		}
	}
}