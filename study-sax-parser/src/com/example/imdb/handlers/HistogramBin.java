package com.example.imdb.handlers;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
public class HistogramBin {

	private int count;

	public HistogramBin() {
	}

	public HistogramBin(int count) {
		this.count = count;
	}

	public void increment() {
		++count;
	}

	public void increment(int value) {
		count += value;
	}

	public int getCount() {
		return count;
	}
}
