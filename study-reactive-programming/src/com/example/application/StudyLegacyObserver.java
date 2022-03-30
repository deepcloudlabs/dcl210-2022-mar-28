package com.example.application;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class StudyLegacyObserver {

	public static void main(String[] args) {
		var tradeEvents = List.of(
			new TradeEvent("orcl", 100,200),
			new TradeEvent("orcl", 101,100),
			new TradeEvent("orcl", 102,200),
			new TradeEvent("orcl", 103,100),
			new TradeEvent("orcl", 104,200)
		);
		var observable = new TradeObservable();
        Observer slowObserver = (o,tradeEvent) -> {
        	try {TimeUnit.SECONDS.sleep(5);}catch (Exception e) {}
        	System.err.println("Slow Observer has processed the event: "+tradeEvent);
        };
        Observer fastObserver = (o,tradeEvent) -> {
        	System.err.println("Fast Observer has processed the event: "+tradeEvent);
        };
        observable.addObserver(slowObserver);
        observable.addObserver(fastObserver);
        tradeEvents.forEach( observable::notifyObservers);
        observable.notifyObservers("elma");
	}

}

class TradeObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}
	
}
record TradeEvent(String symbol, double price, double quantity) {}