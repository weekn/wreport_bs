package weekn.wreport.service.imp;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weekn.wreport.mapper.UserMapper;
import weekn.wreport.model.SysUserModel;

@Service
public class UserServiceImpl {
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	RedisServiceImpl redis_service;
//	public SysUser getUser(){
//		SysUser um=mapper.findByUserName("admin");
//        return um;
//    }
	public SysUserModel getToken(SysUserModel request_user) throws Exception{
		
		SysUserModel user=mapper.findByUserName(request_user.getUsername());
		if(user!=null) {
			String token=UUID.randomUUID().toString();
			redis_service.set(user.getUsername(), token);
			user.setToken(token);
			return user;
		}else {
			throw new Exception("no user");
		}
		
       
    }
	
}
