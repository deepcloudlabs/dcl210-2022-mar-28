package com.example.lottery.service.soap;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.jws.WebService;

import com.example.lottery.jmx.QualityMetric;
import com.example.lottery.jmx.WebServiceQualitySamplerMXBean;
import com.example.lottery.service.LotteryService;

@WebService(endpointInterface = "com.example.lottery.service.LotteryService")
public class StandardLotteryService 
extends Observable
implements LotteryService, WebServiceQualitySamplerMXBean{
    private int counter;
    private long totalResponseTime;
    
	@Override
	public List<Integer> draw(int max, int size) {
		long start = System.nanoTime();
		List<Integer> numbers = ThreadLocalRandom.current()
		        .ints(1, 60)
		        .distinct()
		        .limit(6)
		        .sorted()
		        .boxed()
		        .collect(Collectors.toList());
		long stop = System.nanoTime();
		totalResponseTime += (stop-start);
		counter++;
		double averageResponseTime = (double)totalResponseTime / counter;
		if (averageResponseTime > 200_000.) {
			setChanged();
			notifyObservers(getQualityMetric());
		}
		return numbers;
	}

	@Override
	public void reset() {
		counter=0;
		totalResponseTime=0;
	}

	@Override
	public QualityMetric getQualityMetric() {
		return new QualityMetric(new Date(), counter, (double)totalResponseTime/counter);
	}

}
