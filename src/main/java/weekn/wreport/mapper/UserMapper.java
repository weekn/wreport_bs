package weekn.wreport.mapper;



import weekn.wreport.model.SysUserModel;


public interface  UserMapper {
	public SysUserModel findByUserName(String username);
}
