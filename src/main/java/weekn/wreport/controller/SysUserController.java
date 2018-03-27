package weekn.wreport.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import weekn.wreport.model.ResponseModel;
//import redis.clients.jedis.Jedis;
import weekn.wreport.model.SysUserModel;

import weekn.wreport.service.imp.UserServiceImpl;



@RestController
public class SysUserController {
	@Autowired
	private UserServiceImpl user_service;
	
	
	/**
	 * 用户登录获取token
	 */
	@RequestMapping(value = "/token", method = {RequestMethod.GET,RequestMethod.POST})
	public ResponseModel post(@RequestBody SysUserModel user) {// POST /session # 创建新的会话（登录）
//		System.out.println(request.getHeaders().get("token").get(0));
		ResponseModel response=new ResponseModel();
		try {
			response.setResponse(user_service.getToken(user));
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("登录错误，请确认账号密码");
			
		} 
		
		return  response;
	
	}
	
	@RequestMapping(value = "/token1", method = {RequestMethod.GET,RequestMethod.POST})
	public ResponseModel post1(HttpServletRequest request) {// POST /session # 创建新的会话（登录）
		System.out.println(request.getHeader("token"));
		ResponseModel response=new ResponseModel();
		try {
//			response.setResponse(user_service.getToken(user));
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("登录错误，请确认账号密码");
			
		} 
		
		return  response;
	
	}
	@RequestMapping(value = "/token2", method = {RequestMethod.GET,RequestMethod.POST})
	public ResponseModel post1(String token) {// POST /session # 创建新的会话（登录）
		System.out.println("cc---"+token);
		ResponseModel response=new ResponseModel();
		try {
//			response.setResponse(user_service.getToken(user));
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("登录错误，请确认账号密码");
			
		} 
		
		return  response;
	
	}
}
