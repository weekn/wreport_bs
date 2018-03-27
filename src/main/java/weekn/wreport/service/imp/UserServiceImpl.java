package weekn.wreport.service.imp;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weekn.wreport.dao.UserDao;
import weekn.wreport.model.SysUserModel;
import weekn.wreport.tools.JsonUtils;

@Service
public class UserServiceImpl {
	@Autowired
	private UserDao mapper;
	
	@Autowired
	RedisServiceImpl redis_service;
//	public SysUser getUser(){
//		SysUser um=mapper.findByUserName("admin");
//        return um;
//    }
	public SysUserModel getToken(SysUserModel request_user) throws Exception{
		
		SysUserModel user=mapper.findByUserName(request_user.getUsername(),request_user.getPassword());
		if(user!=null) {
			String token=UUID.randomUUID().toString();
			user.setToken(token);
			String user_json=JsonUtils.encode(user);
			redis_service.set(token, user_json);
			
			return user;
		}else {
			throw new Exception("no user");
		}
		
       
    }
	
}
