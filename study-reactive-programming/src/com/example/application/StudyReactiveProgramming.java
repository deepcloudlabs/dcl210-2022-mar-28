package com.example.application;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class StudyReactiveProgramming {

	public static void main(String[] args) {
		System.err.println("Application has just started.");
		var tradeEvents = List.of(
			new TradeEvent("orcl", 100,200),
			new TradeEvent("orcl", 101,100),
			new TradeEvent("orcl", 102,200),
			new TradeEvent("orcl", 103,100),
			new TradeEvent("orcl", 104,200)
		);
        try (var publisher = new SubmissionPublisher<TradeEvent>()) {
			publisher.subscribe(new SlowSubscriber());
			publisher.subscribe(new FastSubscriber());
			tradeEvents.forEach( publisher::submit );
			// Not allowed: publisher.submit("elma");
		}
        try {TimeUnit.SECONDS.sleep(30);}catch (Exception e) {}
        System.err.println("Application is done.");
	}

}

class SlowSubscriber implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("SlowSubscriber has just subscribed to the event!");
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent tradeEvent) {
    	try {TimeUnit.SECONDS.sleep(5);}catch (Exception e) {}		
    	System.err.println("SlowSubscriber has processed the event: "+tradeEvent);
		this.subscription.request(1);		    	
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println("SlowSubscriber has failed: "+throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.err.println("SlowSubscriber has just completed!");		
	}
	
}

class FastSubscriber implements Flow.Subscriber<TradeEvent> {
	
	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("FastSubscriber has just subscribed to the event!");
		this.subscription = subscription;
		this.subscription.request(1);
	}
	
	@Override
	public void onNext(TradeEvent tradeEvent) {
		System.err.println("FastSubscriber has processed the event: "+tradeEvent);	
		this.subscription.request(1);		
	}
	
	@Override
	public void onError(Throwable throwable) {
		System.err.println("FastSubscriber has failed: "+throwable.getMessage());
	}
	
	@Override
	public void onComplete() {
		System.err.println("FastSubscriber has just completed!");		
	}
	
}