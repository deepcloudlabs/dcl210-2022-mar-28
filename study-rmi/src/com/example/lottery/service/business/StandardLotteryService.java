package com.example.lottery.service.business;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.example.lottery.service.LotteryService;

@SuppressWarnings("serial")
public class StandardLotteryService 
extends UnicastRemoteObject
implements LotteryService {
    
	public StandardLotteryService() throws RemoteException {
	}

	@Override
	public List<Integer> draw(int max, int size) throws RemoteException {
		return ThreadLocalRandom.current()
		        .ints(1, 60)
		        .distinct()
		        .limit(6)
		        .sorted()
		        .boxed()
		        .collect(Collectors.toList());
	}

}
