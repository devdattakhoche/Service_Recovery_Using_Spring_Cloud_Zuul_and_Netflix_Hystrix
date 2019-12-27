package com.example.microservice.service1.path1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.example.microservice.service1.list.*;

@RestController
public class firstController {
	
	Logger log = LoggerFactory.getLogger(firstController.class);
	
	@Autowired
	private ArrayOfDev ArrayOfDev;    
	
	 @KafkaListener(topics = "Kafka_Example2", groupId = "group_json",containerFactory = "userKafkaListenerFactory")
	 public  void getservice1(DevList devList) throws InterruptedException
	 {
		 System.out.println(devList.getKey());
		 DevList d = devList;
		 ArrayOfDev.insertDevList(d);
		 log.info("all messages are consumed by kafka");
	}
	 
	 
	@RequestMapping("/first-service")
	@HystrixCommand(fallbackMethod = "getfallbackMethod")
	public  ArrayOfDev getservice2()
	{
		log.info("getservice2 is called...");
		return ArrayOfDev;
	}
	 
	public ArrayOfDev getfallbackMethod(Throwable hystrixCommand) throws InterruptedException 
	{	
		log.info("getfallbackMethod is called...");
		List<DevList> fallBackMessage = new ArrayList<DevList>(Arrays.asList(new DevList(0,"no name")));
		ArrayOfDev.setDevList(fallBackMessage);
		
		return  ArrayOfDev;
	}
}