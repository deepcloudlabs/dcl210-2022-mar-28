package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class StudyParallelProgramming extends Object {
	private static final String FORMAT = "Method: %20s Volume: %12.1f Duration: %12d";
	private static final int DATA_SIZE = 400_000_000;
	private static final int CORES = Runtime.getRuntime().availableProcessors();
	private static final int DATA_SIZE_PER_CORE = DATA_SIZE / CORES;
	private static final int LOOP_SIZE = 12;
	private static final Random random = new Random();

	private static List<Trade> trades = new ArrayList<>(DATA_SIZE);

	static {
		System.err.println("Initializing Trade Data...");
		for (var i = 0; i < DATA_SIZE; ++i)
			trades.add(createRandomTrade());
		System.err.println("Initializing Trade Data...Done.");
	}

	public static void computeVolumeSerialImperative() {
		var begin = System.nanoTime();
		var volume = 0.;
		for (var trade : trades) {
			volume += trade.price * trade.quantity;
		}
		var end = System.nanoTime();
		System.err.println(String.format(FORMAT, "Serial Imperative", volume, (end - begin)));
	}

	public static void computeVolumeSerialFunctional() {
		var begin = System.nanoTime();
		var volume = trades.stream().mapToDouble(Trade::getVolume).sum();
		var end = System.nanoTime();
		System.err.println(String.format(FORMAT, "Serial Functional", volume, (end - begin)));
	}

	public static void computeVolumeParallelPureThread() {
		ExecutorService es = Executors.newFixedThreadPool(CORES);
		var begin = System.nanoTime();
		var futures = new ArrayList<Future<Double>>(CORES);
		for (int i = 0, start = 0; i < CORES; ++i, start += DATA_SIZE_PER_CORE) {
			var task = new VolumeIndicatorTask(trades, start, DATA_SIZE_PER_CORE);
			futures.add(es.submit(task));
		}
		var volume = 0.;
		for (var future : futures) {
			try {
				volume += future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		var end = System.nanoTime();
		System.err.println(String.format(FORMAT, "Parallel Pure Thread", volume, (end - begin)));
		es.shutdown();
	}

	public static void computeVolumeParallelForkJoinPool() {
		ForkJoinPool fjp = new ForkJoinPool();
		var begin = System.nanoTime();
		VolumeIndicatorRecursiveTask task = new VolumeIndicatorRecursiveTask(trades, 0, trades.size() - 1);
		var volume = fjp.invoke(task);
		var end = System.nanoTime();
		System.err.println(String.format(FORMAT, "Parallel Fork/Join", volume, (end - begin)));
	}

	public static void computeVolumeParallelStream() {
		var begin = System.nanoTime();
		var volume = trades.stream().parallel().mapToDouble(Trade::getVolume).sum();
		var end = System.nanoTime();
		System.err.println(String.format(FORMAT, "Parallel Stream", volume, (end - begin)));
	}

	public static void main(String[] args) {

		for (var i = 0; i < LOOP_SIZE; ++i) {
			computeVolumeParallelForkJoinPool();
			//System.gc();
		}
		for (var i = 0; i < LOOP_SIZE; ++i) {
			computeVolumeSerialImperative();
			//System.gc();
		}
		for (var i = 0; i < LOOP_SIZE; ++i) {
			computeVolumeSerialFunctional();
			//System.gc();
		}
		for (var i = 0; i < LOOP_SIZE; ++i) {
			computeVolumeParallelPureThread();
			//System.gc();
		}
		for (var i = 0; i < LOOP_SIZE; ++i) {
			computeVolumeParallelStream();
			//System.gc();
		}
	}

	private static Trade createRandomTrade() {
//		return new Trade(createRandomPQ(), createRandomPQ());
		return new Trade(1, 1);
	}

	@SuppressWarnings("unused")
	private static double createRandomPQ() {
		return random.nextDouble() * 50 + 50;
	}

}

class Trade { // 12B 4B 8B 8B: 32B x 1M
	public double price;
	public double quantity;

	public Trade(double price, double quantity) {
		this.price = price;
		this.quantity = quantity;
	}

	public double getVolume() {
		return price * quantity;
	}
}

class VolumeIndicatorTask implements Callable<Double> {
	private List<Trade> trades;
	private int start;
	private int size;

	public VolumeIndicatorTask(List<Trade> trades, int start, int size) {
		this.trades = trades;
		this.start = start;
		this.size = size;
	}

	@Override
	public Double call() throws Exception {
		double volume = 0.;
		for (int i = start, j = 0; j < size; ++i, ++j) {
			volume += trades.get(i).getVolume();
		}
		return volume;
	}

}

@SuppressWarnings("serial")
class VolumeIndicatorRecursiveTask extends RecursiveTask<Double> {
	private List<Trade> trades;
	private int begin;
	private int end;

	public VolumeIndicatorRecursiveTask(List<Trade> trades, int begin, int end) {
		this.trades = trades;
		this.begin = begin;
		this.end = end;
	}

	@Override
	protected Double compute() {
		int size = end - begin;
		if (size <= 50_000) {
			double volume = 0.;
			for (int i = begin; i <= end; ++i) {
				volume += trades.get(i).getVolume();
			}
			return volume;
		}
		var left = new VolumeIndicatorRecursiveTask(trades, begin, begin + size / 2);
		var right = new VolumeIndicatorRecursiveTask(trades, begin + size / 2 + 1, end);
		invokeAll(left, right);
		try {
			return left.get() + right.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return 0.;
	}

}