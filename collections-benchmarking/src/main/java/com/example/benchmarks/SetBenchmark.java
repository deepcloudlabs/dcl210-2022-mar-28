package com.example.benchmarks;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class SetBenchmark {

	private static final int DATA_SIZE= 250_000;

	@Benchmark
	public void add_HashSet() {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < DATA_SIZE; ++i)
			set.add(i);
	}
	
	@Benchmark
	public void add_LinkedHashSet() {
		Set<Integer> set = new LinkedHashSet<>();
		for (int i = 0; i < DATA_SIZE; ++i)
			set.add(i);
	}
	
	@Benchmark
	public void add_TreeHashSet() {
		Set<Integer> set = new TreeSet<>();
		for (int i = 0; i < DATA_SIZE; ++i)
			set.add(i);
	}	

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(SetBenchmark.class.getSimpleName())
				.warmupIterations(5)
				.measurementIterations(10)
				.forks(1)
				.build();

		new Runner(opt).run();
	}
}
