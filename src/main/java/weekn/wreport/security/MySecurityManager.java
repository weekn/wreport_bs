package weekn.wreport.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import weekn.wreport.model.SysUserModel;
import weekn.wreport.service.RedisService;
import weekn.wreport.tools.JsonUtils;

public class MySecurityManager {
	private SysUserModel user;
	
	public MySecurityManager start(HttpServletRequest request,RedisService redis_service) throws MyException_noLogin {
		String token =request.getHeader("token");
		if(token==null) {
			throw new MyException_noLogin("redis找不到对应token");
		}
		try {
			this.user=JsonUtils.decode(redis_service.get(token).toString(), SysUserModel.class);
			return this;
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyException_noLogin("json解析出错吗");
		}catch (NullPointerException e) {
			throw new MyException_noLogin("redis找不到对应token");
		}
	}
	public SysUserModel end() {
		return this.user;
	}
	public MySecurityManager needRoles() {
		return this;
	}
}
