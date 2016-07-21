package com.andy.http;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;



public class PhoneClient {
	//get请求
	public void get(String phone) throws Exception {
		// 创建一个浏览器对象
		HttpClient client = new HttpClient();
		// 创建一个get请求
		GetMethod get = new GetMethod("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo?mobileCode="+phone+"&userID=");
		//制定请求参数类型
		get.setRequestHeader("Content-Type", "text/xml;charset=utf-8");
		//发送get请求
		int status = client.executeMethod(get);
		//输出请求后的状体
		String responseString = get.getResponseBodyAsString();
			
		System.out.println("状态码："+status);
		System.out.println(responseString);
	}
	
	//post请求
	public void post(String phone) throws Exception {
		// 创建一个浏览器对象
		HttpClient client = new HttpClient();
		// 创建一个post请求
		PostMethod post = new PostMethod("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo");
		//制定请求参数的类型
		post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		
		//设置请求参数
		post.setParameter("mobileCode", phone); 
		post.setParameter("userID", ""); 
		
		//发送post请求
		int status = client.executeMethod(post);
		String responseString = post.getResponseBodyAsString();	
		
		System.out.println("状态码："+status);
		System.out.println(responseString);
	}
	
	//soap请求
		@SuppressWarnings("deprecation")
		public void soap() throws Exception {
			// 创建一个浏览器对象
			HttpClient client = new HttpClient();
			// 创建一个post请求
			PostMethod post = new PostMethod("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx");
			
			//制定请求参数类型
			post.setRequestHeader("Content-Type", "text/xml; charset=utf-8");
			post.setRequestBody(new FileInputStream(new File("L:/project/soap.txt"))); 
			
			//发送post请求
			int status = client.executeMethod(post);
			String responseString = post.getResponseBodyAsString();	
			
			System.out.println("状态码："+status);
			System.out.println(responseString);
		}
	
	
	public static void main(String[] args) throws Exception {
		PhoneClient client = new PhoneClient();
		client.get("1382738309");
		client.post("1397627929");
		client.soap();
	}
	

}
