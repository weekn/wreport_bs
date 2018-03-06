package com.nfjd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nfjd.mapper.UserMapper;
import com.nfjd.model.UserModel;

@Service
public class UserService {
	@Autowired
	private UserMapper mapper;
	
	public List<UserModel> getUser(){
		List<UserModel> um=mapper.getUser();
        return um;
    }

	
}
