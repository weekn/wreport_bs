package weekn.wreport.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import weekn.wreport.model.SysUserModel;
import weekn.wreport.model.TeamInfoModel;


public interface  UserDao {
	public SysUserModel findByUserName(@Param("username")String username,@Param("password")String password);
	public List<TeamInfoModel> getUserTeamMate(@Param("team_id")int team_id);
}
