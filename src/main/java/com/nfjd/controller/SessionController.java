package com.nfjd.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nfjd.model.UserModel;

@RestController
public class SessionController {
	@RequestMapping(value = "/session",method = RequestMethod.POST)
	public String post(UserModel user,HttpSession session){//POST /session # 创建新的会话（登录） 
		if(user.getAccount()!=null){
			session.setAttribute("user", user);
			return "1";
		}else{
			return "0";
		}
		
		
		
	}
	
	@RequestMapping(value = "/session",method = RequestMethod.DELETE)
	public String delete(HttpSession session){
		session.removeAttribute("user");
		return null;
	}
	
	@RequestMapping(value = "/session2", method = RequestMethod.POST)  
    public String loginByPost(@RequestParam(value = "account", required = false) String account,  
            @RequestParam(value = "pwd", required = false) String pwd) {  
        System.out.println("hello post");  
        return (account+" "+pwd);  
    } 

}

//GET /session # 获取会话信息 
//POST /session # 创建新的会话（登录） 
//PUT /session # 更新会话信息 
//DELETE /session # 销毁当前会话（注销）
//
//GET /user/:id # 获取id用户的信息 
//POST /user # 创建新的用户（注册） 
//PUT /user/:id # 更新id用户的信息 
//DELETE /user/:id # 删除id用户（注销）