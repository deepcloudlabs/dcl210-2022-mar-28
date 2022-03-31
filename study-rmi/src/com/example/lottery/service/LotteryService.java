package com.example.lottery.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LotteryService extends Remote {
	List<Integer> draw(int max, int size) throws RemoteException;
}
