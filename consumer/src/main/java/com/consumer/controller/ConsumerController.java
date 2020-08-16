package com.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class ConsumerController {
	
	//@Autowired
	//private DiscoveryClient dc;
	
	@Autowired
	private LoadBalancerClient lbc;
	
	public void accessEmployee() {
		
		/*
		 * List<ServiceInstance> s=dc.getInstances("FIRST-PRODUCER");
		 * System.out.println("-----------------------------:"+s.size());
		 * ServiceInstance s1 = s.get(0);
		 */
		
		ServiceInstance s2 = lbc.choose("FIRST-PRODUCER");
		ServiceInstance s3 = lbc.choose("MANAGER-PRODUCER");
		ServiceInstance s4 = lbc.choose("PRODUCT-PRODUCER");
		//System.out.println("jyothi="+s4.getUri().toString());
		//ServiceInstance s1=s.get(0);
		
		//String ur=s1.getUri().toString();
		String ur=s2.getUri().toString();
		String ur1=s3.getUri().toString();
		//String ur2="";
		String ur2=s4.getUri().toString();
		//ur=ur+"/allEmployeejpa";
		ur=ur+"/allEmployeejdbc";
		ur1=ur1+"/mgrdetails";
		ur2=ur2+"/allproductjpa";
		//System.out.println("url"+ur);
		String output = "";
		RestTemplate client=new RestTemplate();
		ResponseEntity<String> result=null;
		
		result=client.exchange(ur,HttpMethod.GET,createHeader(),String.class);
		output="Service1 Ouput:"+result.getBody();
		
		result=client.exchange(ur1,HttpMethod.GET,createHeader(),String.class);
		String output2=" Service2 Ouput:"+result.getBody();
		
		result=client.exchange(ur2,HttpMethod.GET,createHeader(),String.class);
		String output3=" Service3 Ouput:"+result.getBody();
		System.out.println(output+output2+output3);
	}
	
	public static HttpEntity<?> createHeader(){
		HttpHeaders h=new HttpHeaders();
		h.set("Accept",MediaType.APPLICATION_JSON_VALUE);
		
		return new HttpEntity<>(h);
	}
}
