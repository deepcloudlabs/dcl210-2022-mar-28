package com.example.lottery.jmx;

import java.util.Date;

public class QualityMetric { // immutable
	private final Date date;
	private final int counter;
	private final double averageResponseTime;

	public QualityMetric(Date date, int counter, double averageResponseTime) {
		this.date = date;
		this.counter = counter;
		this.averageResponseTime = averageResponseTime;
	}

	public Date getDate() {
		return date;
	}

	public int getCounter() {
		return counter;
	}

	public double getAverageResponseTime() {
		return averageResponseTime;
	}

	@Override
	public String toString() {
		return "QualityMetric [date=" + date + ", counter=" + counter + ", averageResponseTime=" + averageResponseTime
				+ "]";
	}

}
