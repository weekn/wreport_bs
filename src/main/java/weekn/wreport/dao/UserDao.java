package weekn.wreport.dao;



import org.apache.ibatis.annotations.Param;

import weekn.wreport.model.SysUserModel;


public interface  UserDao {
	public SysUserModel findByUserName(@Param("username")String username,@Param("password")String password);
}
