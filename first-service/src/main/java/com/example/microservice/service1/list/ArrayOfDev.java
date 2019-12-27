package com.example.microservice.service1.list;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class ArrayOfDev {  
	
	private List<DevList> devList = new ArrayList<DevList>(Arrays.asList());

	public List<DevList> getDevList() {
		return devList;
	}

	public void setDevList(List<DevList> devList) {
		this.devList = devList;
	}
	
	public void insertDevList(DevList d) {
		devList.add(d);
	}
}
