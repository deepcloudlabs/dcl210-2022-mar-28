package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyArraySummation {
	private static final int DATA_SIZE = 400_000_000;
	private static final int CPUS = Runtime.getRuntime().availableProcessors();
	private static final List<Double> DATA = new ArrayList<>(DATA_SIZE);
	private static final ExecutorService POOL = Executors.newFixedThreadPool(CPUS);
	static {
		System.err.println("Initializing Data...");
		for (int i = 0; i < DATA_SIZE; i++) {
			DATA.add(1.);
		}
		System.err.println("Initializing Data...Done");
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; ++i)
			solveSumParallelStream();
		for (int i = 0; i < 10; ++i)
			solveSumParallel();
		for (int i = 0; i < 10; ++i)
			solveSumStream();
		for (int i = 0; i < 10; ++i)
			solveSumSerial();
		POOL.shutdown();
	}

	private static void solveSumStream() {
		long begin = System.currentTimeMillis();
		double sum = DATA.stream().mapToDouble(Double::doubleValue).sum();
		long end = System.currentTimeMillis();
		System.err.println(String.format("%24s %16.8f %16d", "Stream[Serial]", sum, (end - begin)));
	}

	private static void solveSumParallelStream() {
		long begin = System.currentTimeMillis();
		double sum = DATA.stream().mapToDouble(Double::doubleValue).sum();
		long end = System.currentTimeMillis();
		System.err.println(String.format("%24s %16.8f %16d", "Stream[Parallel]", sum, (end - begin)));
	}

	private static void solveSumParallel() {
		int size = DATA_SIZE / CPUS;
		List<Future<Double>> futures = new ArrayList<>(CPUS);
		long begin = System.currentTimeMillis();
		for (int i = 0, start = 0; i < CPUS; ++i, start += size) {
			SumTask task = new SumTask(DATA, start, size);
			futures.add(POOL.submit(task));
		}
		double sum = 0.;
		for (Future<Double> future : futures) {
			try {
				sum += future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.err.println(String.format("%24s %16.8f %16d", "Parallel", sum, (end - begin)));
	}

	private static void solveSumSerial() {
		double sum = 0.;
		long start = System.currentTimeMillis();
		for (int i = 0; i < DATA.size(); i++) {
			sum += DATA.get(i);
		}
		long stop = System.currentTimeMillis();
		System.err.println(String.format("%24s %16.8f %16d", "Serial", sum, (stop - start)));
	}

}

class SumTask implements Callable<Double> {
	private List<Double> data;
	private int start;
	private int size;

	public SumTask(List<Double> data, int start, int size) {
		this.data = data;
		this.start = start;
		this.size = size;
	}

	@Override
	public Double call() throws Exception {
		double sum = 0L;
		for (int i = start, j = 0; j < size; i++, j++) {
			sum += data.get(i);
		}
		return sum;
	}

	@Override
	public String toString() {
		return "SumTask [start=" + start + ", size=" + size + "]";
	}

}
