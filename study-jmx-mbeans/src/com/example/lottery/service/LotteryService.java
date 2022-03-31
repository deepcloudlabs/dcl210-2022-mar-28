package com.example.lottery.service;

import java.util.List;

import javax.jws.WebService;

@WebService
public interface LotteryService {
	List<Integer> draw(int max, int size);
}
