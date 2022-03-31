package com.example.service.rest;

import java.util.List;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/lottery")
public class LotteryService {
	@GET
	@Produces("application/json")
	public List<Integer> draw(){
		return new Random().ints(1,50)
				   .distinct()
				   .limit(6)
				   .sorted()
				   .boxed()
				   .toList();
	}
}
