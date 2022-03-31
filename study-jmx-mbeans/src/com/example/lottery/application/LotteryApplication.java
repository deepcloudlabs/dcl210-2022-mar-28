package com.example.lottery.application;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.xml.ws.Endpoint;

import com.example.lottery.jmx.WebServiceQualitySampler;
import com.example.lottery.service.soap.StandardLotteryService;

public class LotteryApplication {

	public static void main(String[] args) throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		StandardLotteryService lotteryService = new StandardLotteryService();
		ObjectName mBeanName = new ObjectName("com.example.jmx:type=WebServiceQuality");
		WebServiceQualitySampler sampler = new WebServiceQualitySampler(lotteryService);
		mBeanServer.registerMBean(sampler, mBeanName );
		lotteryService.addObserver(sampler);
		System.err.println("Lottery Service is running at 8100");
		Endpoint.publish("http://localhost:8100/lottery", lotteryService);
	}

}
