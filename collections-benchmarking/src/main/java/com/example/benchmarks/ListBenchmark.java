package com.example.benchmarks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.collections.impl.list.mutable.FastList;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class ListBenchmark {

	private static final int DATA_SIZE = 100_000;

	@Benchmark
	public void addTail_FastList() {
		List<Integer> list = new FastList<>();
		for (int i = 0; i < DATA_SIZE; ++i)
			list.add(i);
	}
	
	@Benchmark
	public void addTail_FastList_FullCapacity() {
		List<Integer> list = new FastList<>(DATA_SIZE);
		for (int i = 0; i < DATA_SIZE; ++i)
			list.add(i);
	}
	
	@Benchmark
	public void addHead_FastList() {
		List<Integer> list = new FastList<>();
		for (int i = 0; i < DATA_SIZE; ++i)
			list.add(0,i);
	}
	
	@Benchmark
	public void addHead_FastList_FullCapacity() {
		List<Integer> list = new FastList<>(DATA_SIZE);
		for (int i = 0; i < DATA_SIZE; ++i)
			list.add(0,i);
	}
	
	@Benchmark
	public void addTail_ArrayList() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < DATA_SIZE; ++i)
			list.add(i);
	}

	@Benchmark
	public void addTail_ArrayList_fullCapacity() {
		List<Integer> list = new ArrayList<>(DATA_SIZE);
		for (int i = 0; i < DATA_SIZE; ++i)
			list.add(i);
	}

	@Benchmark
	public void addTail_ArrayDeque() {
		Deque<Integer> list = new ArrayDeque<>();
		for (int i = 0; i < DATA_SIZE; ++i)
			list.addLast(i);
	}

	@Benchmark
	public void addTail_ArrayDeque_fullCapacity() {
		Deque<Integer> list = new ArrayDeque<>(DATA_SIZE);
		for (int i = 0; i < DATA_SIZE; ++i)
			list.addLast(i);
	}

	@Benchmark()
	public void addHead_ArrayList() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < DATA_SIZE; ++i)
			list.add(0, i);
	}

	@Benchmark()
	public void addHead_ArrayList_fullCapacity() {
		List<Integer> list = new ArrayList<>(DATA_SIZE);
		for (int i = 0; i < DATA_SIZE; ++i)
			list.add(0, i);
	}

	@Benchmark
	public void addHead_ArrayDeque() {
		Deque<Integer> list = new ArrayDeque<>();
		for (int i = 0; i < DATA_SIZE; ++i)
			list.addFirst(i);
	}

	@Benchmark
	public void addHead_ArrayDeque_fullCapacity() {
		Deque<Integer> list = new ArrayDeque<>(DATA_SIZE);
		for (int i = 0; i < DATA_SIZE; ++i)
			list.addFirst(i);
	}

	@Benchmark
	public void addTail_LinkedList() {
		List<Integer> list = new LinkedList<>();
		for (int i = 0; i < DATA_SIZE; ++i)
			list.add(i);
	}

	@Benchmark
	public void addHead_LinkedList() {
		List<Integer> list = new LinkedList<>();
		for (int i = 0; i < DATA_SIZE; ++i)
			list.add(0, i);
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(ListBenchmark.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(5).forks(1).build();

		new Runner(opt).run();
	}
}
